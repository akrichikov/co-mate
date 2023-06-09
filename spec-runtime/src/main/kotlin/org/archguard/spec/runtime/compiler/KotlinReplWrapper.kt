package org.archguard.spec.runtime.compiler

import org.archguard.meta.dsl.rest_api
import org.jetbrains.kotlinx.jupyter.EvalRequestData
import org.jetbrains.kotlinx.jupyter.ReplForJupyter
import org.jetbrains.kotlinx.jupyter.api.Code
import org.jetbrains.kotlinx.jupyter.defaultRepositoriesCoordinates
import org.jetbrains.kotlinx.jupyter.libraries.EmptyResolutionInfoProvider
import org.jetbrains.kotlinx.jupyter.messaging.NoOpDisplayHandler
import org.jetbrains.kotlinx.jupyter.repl.creating.createRepl
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.script.experimental.jvm.util.KotlinJars

class KotlinReplWrapper {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val repl: ReplForJupyter

    init {
        this.repl = this.makeEmbeddedRepl()
    }

    private fun makeEmbeddedRepl(): ReplForJupyter {
        val property = System.getProperty("java.class.path")
        var embeddedClasspath: MutableList<File> = property.split(File.pathSeparator).map(::File).toMutableList()

        embeddedClasspath = embeddedClasspath.distinctBy { it.name }
            .filter {
                // because it conflicts with `logback-classic-1.2.3.jar` from `kotlinx-jupyter-core`
                !(it.name.startsWith("logback-classic-") && it.name.endsWith(".jar"))
            }
                as MutableList<File>

        logger.info("classpath: $embeddedClasspath")

        return createRepl(
            EmptyResolutionInfoProvider,
            embeddedClasspath,
            mavenRepositories = defaultRepositoriesCoordinates,
            libraryResolver = extendLibraries(),
            displayHandler = NoOpDisplayHandler,
            isEmbedded = true
        )
    }

    fun eval(code: Code, jupyterId: Int = -1, storeHistory: Boolean = true) =
        repl.evalEx(EvalRequestData(code, jupyterId, storeHistory))
}
