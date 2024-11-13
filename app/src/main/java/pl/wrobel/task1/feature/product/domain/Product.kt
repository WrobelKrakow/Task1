package pl.wrobel.task1.feature.product.domain

data class Product(
    val id: Long,
    val price: String,
    val productName: String,
    val quantity: Int,
)