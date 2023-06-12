package org.archguard.meta.base

@DslMarker
annotation class SpecDsl

interface AtomicAction<T> {
    val name: String
    fun exec(input: T): Any {
        return false
    }
}

interface BaseLlmVerifier<T : Any> : AtomicAction<T> {
    override fun exec(input: T): RuleResult {
        return exec(input)
    }
}

interface Element

