package pl.wrobel.task1.feature.product.data.local.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.wrobel.task1.feature.product.domain.Product

@Entity
@Keep
data class ProductCached(
    @PrimaryKey val id: Long,
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