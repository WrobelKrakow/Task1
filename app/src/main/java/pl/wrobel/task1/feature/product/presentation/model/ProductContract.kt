package pl.wrobel.task1.feature.product.presentation.model

import pl.wrobel.base.data.ViewEvent
import pl.wrobel.base.data.ViewState

class ProductContract {
    sealed class State : ViewState {
        data object Empty : State()
        data class Error(val exception: Throwable) : State()
        data class Loaded(val products: List<ProductDisplayable>) : State()
    }

    sealed class Event : ViewEvent {
        data object Load : Event()
        data class ChangeQuantity(val product: ProductDisplayable, val valueChange: Int) : Event()
    }
}