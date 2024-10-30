package pl.wrobel.task1.feature.product.domain

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Product(
    val id: Long,
    val price: String,
    val productName: String,
    val quantity: Int,
)