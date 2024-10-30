package pl.wrobel.base.data

sealed class ProgressResult(val progress: Float) {
    data object Success : ProgressResult(1f)
    data class Loading(val current: Int, val total: Int) : ProgressResult(current.toFloat() / total)
    data class Error(val error: String) : ProgressResult(-1f)
}
