package pl.wrobel.task1.feature.product.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import pl.wrobel.task1.feature.product.presentation.model.ProductContract

@Composable
fun ProductItemsScreen(
    productsViewModel: ProductsViewModel,
) {
    val state = productsViewModel.state.collectAsState()

    when (val value = state.value) {
        is ProductContract.State.Empty -> ProductItemsList()

        is ProductContract.State.Error -> ShowError()

        is ProductContract.State.Loaded -> ProductItemsList(
            products = value.products,
            changeQuantity = { product, valueChange -> productsViewModel.setEvent(ProductContract.Event.ChangeQuantity(product, valueChange)) },
            showDetails = { product -> productsViewModel.setEvent(ProductContract.Event.NavigateDetails(product)) },)

    }


}

@Composable
fun ShowError() {
    //todo
}
