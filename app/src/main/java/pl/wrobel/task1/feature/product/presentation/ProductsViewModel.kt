package pl.wrobel.task1.feature.product.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.wrobel.base.BaseViewModel
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.presentation.model.ProductContract.Event
import pl.wrobel.task1.feature.product.presentation.model.ProductContract.State
import pl.wrobel.task1.feature.product.presentation.model.ProductDisplayable
import pl.wrobel.task1.feature.product.usecase.GetProductsUseCase
import pl.wrobel.task1.feature.product.usecase.UpdateProductUseCase
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : BaseViewModel<State, Event>() {
    override fun setInitialState(): State = State.Empty
    private fun load() {
        getProductsUseCase(Unit, scope = viewModelScope) { result ->
            result.onSuccess {
                viewModelScope.launch(Dispatchers.IO) { it.collect { onLoaded(it) } }
            }.onFailure { throwable: Throwable -> setState { State.Error(throwable) } }

        }
    }

    private fun onLoaded(products: List<Product>) {
        setState { State.Loaded(products.map { ProductDisplayable(it) }) }
    }

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Load -> load()
            is Event.ChangeQuantity -> changeQuantity(event.product, event.valueChange)
        }
    }

    private fun changeQuantity(product: ProductDisplayable, valueChange: Int) {
        updateProductUseCase.invoke(product.product.copy(quantity = product.product.quantity + valueChange), scope = viewModelScope)
    }

}
