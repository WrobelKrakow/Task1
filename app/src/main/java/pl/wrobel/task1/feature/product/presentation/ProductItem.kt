package pl.wrobel.task1.feature.product.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wrobel.task1.core.presentation.theme.Task1Theme
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.presentation.model.ProductDisplayable

val sampleProduct = Product(id = 1, price = "100$", productName = "Name1", quantity = 5)

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productDisplayable: ProductDisplayable = ProductDisplayable(product = sampleProduct),
    changeQuantity: (ProductDisplayable, Int) -> Unit = { _, _ -> },
    showDetails: (ProductDisplayable) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { showDetails(productDisplayable) },
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = productDisplayable.product.productName,
                fontSize = 16.sp,
            )
            Text(
                text = "Price:",
                fontSize = 14.sp,
            )
            Text(
                text = productDisplayable.product.price,
                fontSize = 14.sp,
            )
            Text(
                text = "Quantity:",
                fontSize = 14.sp,
            )
            Text(
                text = productDisplayable.product.quantity.toString(),
                fontSize = 14.sp,
            )
        }
        Row(
            modifier = modifier
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { showDetails(productDisplayable) },
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(onClick = { changeQuantity(productDisplayable, 1) }) {
                Text(
                    text = "Add item",
                )
            }
            Button(onClick = { changeQuantity(productDisplayable, -1) }) {
                Text(
                    text = "Remove item",
                )
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
private fun Preview() = Task1Theme {
    Column {
        ProductItem()
    }
}