package pl.wrobel.task1.feature.product.data.remote.model

import androidx.annotation.Keep
import pl.wrobel.task1.feature.product.domain.Product

@Keep
data class ProductRemote(
    val id: Long,
    val price: String,
    val productName: String,
    val quantity: Int
) {
    constructor(product: Product) : this(
        id = product.id,
        price = product.price,
        productName = product.productName,
        quantity = product.quantity,
    )

    fun toProduct() = Product(
        id = id,
        price = price,
        productName = productName,
        quantity = quantity,
    )
}