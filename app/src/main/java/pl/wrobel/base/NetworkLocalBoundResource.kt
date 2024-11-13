package pl.wrobel.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

inline fun <DB, REMOTE> networkLocalBoundResource(
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline shouldFetchFromRemote: suspend (DB?) -> Boolean = { true },
    crossinline fetchFromRemote: suspend () -> Response<REMOTE>,
    crossinline saveFetchResult: suspend (REMOTE) -> Unit,
) = flow<Resource<DB>> {
    emit(Resource.loading())
    val data = fetchFromLocal().firstOrNull()
    if (shouldFetchFromRemote(data)) {
        if (data != null) emit(Resource.success(data))
        try {
            val fetchResult = fetchFromRemote()
            if (fetchResult.isSuccessful) {
                fetchResult.body()?.let { saveFetchResult(it) }
                emitAll(fetchFromLocal().map { dbData -> Resource.success(dbData) })
            } else {
                val msg = fetchResult.errorBody()?.stringSuspending()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    fetchResult.message()
                } else {
                    msg
                }
                emit(if (data != null) Resource.remoteError(data, errorMsg) else Resource.totalError(errorMsg))
            }
        } catch (e: Exception) {
            emit(if (data != null) Resource.remoteError(data, e.message ?: "total error") else Resource.totalError(e.toString()))
        }
    } else {
        emitAll(fetchFromLocal().map { dbData -> Resource.success(dbData) })
    }
}

suspend fun ResponseBody.stringSuspending(): String = withContext(Dispatchers.IO) { string() }
