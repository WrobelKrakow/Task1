package pl.wrobel.base

abstract class DeferUseCase<out Type, in Params> {

    abstract fun action(params: Params): Type

    operator fun invoke(params: Params) = action(params)
}
