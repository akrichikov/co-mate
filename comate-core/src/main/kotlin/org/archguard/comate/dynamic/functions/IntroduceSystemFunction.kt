package org.archguard.comate.dynamic.functions

import org.archguard.comate.command.ComateContext

@ComateFunction
class IntroduceSystemFunction(override val context: ComateContext) : DyFunction {
    override fun explain(): String {
        return "Introduce system based on README.md, project dependencies."
    }

    override fun execute(): FunctionResult.Success<Boolean> {
        return FunctionResult.Success(true)
    }

    override fun parameters(): HashMap<String, String> = hashMapOf(

    )
}
