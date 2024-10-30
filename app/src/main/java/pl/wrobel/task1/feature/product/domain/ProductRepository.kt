package pl.wrobel.task1.feature.product.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun updateProduct(product: Product)
}