package pl.wrobel.base

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class TotalError<T>(val error: String) : Resource<T>()
    data class RemoteError<T>(val data: T, val error: String) : Resource<T>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> totalError(error: String): Resource<T> = TotalError(error)
        fun <T> remoteError(data: T, error: String): Resource<T> = RemoteError(data, error)
        fun <T> loading(): Resource<T> = Loading()
    }
}
