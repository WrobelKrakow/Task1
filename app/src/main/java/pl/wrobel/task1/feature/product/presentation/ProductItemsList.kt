package pl.wrobel.task1.feature.product.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.wrobel.task1.core.presentation.theme.Task1Theme
import pl.wrobel.task1.feature.product.presentation.model.ProductDisplayable


@Composable
fun ProductItemsList(
    modifier: Modifier = Modifier,
    products: List<ProductDisplayable> = emptyList(),
    changeQuantity: (ProductDisplayable, Int) -> Unit = { _, _ -> },
    showDetails: (ProductDisplayable) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(top = 56.dp),
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(products) {
            ProductItem(
                productDisplayable = it,
                changeQuantity = changeQuantity,
                showDetails = showDetails,
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun Preview() = Task1Theme {
    Column {
        ProductItemsList(products = listOf(ProductDisplayable(product = sampleProduct), ProductDisplayable(product = sampleProduct)))
    }
}