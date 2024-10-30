package pl.wrobel.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> {

    abstract suspend fun action(params: Params, scope: CoroutineScope): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Type>) -> Unit = {},
    ) {
        scope.launch {
            val result = withContext(executionDispatcher) { kotlin.runCatching { action(params, scope) } }
            onResult(result)
        }
    }
}
