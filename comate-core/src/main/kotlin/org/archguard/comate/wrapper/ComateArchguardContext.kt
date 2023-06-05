package org.archguard.comate.wrapper

import org.archguard.scanner.core.client.ArchGuardClient
import org.archguard.scanner.core.client.EmptyArchGuardClient
import org.archguard.scanner.core.sca.ScaContext
import org.archguard.scanner.core.sourcecode.SourceCodeContext

private val archGuardClient = EmptyArchGuardClient()

class ComateScaContext(
    override val client: ArchGuardClient,
    override val path: String,
    override val language: String,
) : ScaContext {
    companion object {
        fun create(path: String, language: String): ScaContext {
            return ComateScaContext(archGuardClient, path, language)
        }
    }
}

class ComateSourceCodeContext(
    override val client: ArchGuardClient,
    override val features: List<String>,
    override val language: String,
    override val path: String,
) : SourceCodeContext {
    companion object {
        fun create(path: String, language: String): SourceCodeContext {
            return ComateSourceCodeContext(archGuardClient, listOf(), language, path)
        }
    }
}