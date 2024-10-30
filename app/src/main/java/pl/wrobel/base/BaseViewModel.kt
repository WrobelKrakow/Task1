package pl.wrobel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import pl.wrobel.base.data.ViewEvent
import pl.wrobel.base.data.ViewState

abstract class BaseViewModel<STATE : ViewState, Event : ViewEvent> : ViewModel() {

    private val initialState: STATE by lazy { setInitialState() }
    abstract fun setInitialState(): STATE

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()
    val currentState: STATE get() = state.value
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    init {
        subscribeToEvents()
    }

    abstract fun handleEvents(event: Event)

    private fun subscribeToEvents() {
        viewModelScope.launch {
            try {
                _event.collect(this@BaseViewModel::handleEvents)
            } catch (e: Exception) {
                if (e.message?.contains("cancelled") == false) {
                    timber.log.Timber.e(e)
                }
            }
        }
    }
}
