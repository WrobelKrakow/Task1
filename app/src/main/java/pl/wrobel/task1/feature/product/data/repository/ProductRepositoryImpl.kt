package pl.wrobel.task1.feature.product.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pl.wrobel.base.data.local.LocalJsonDataProvider
import pl.wrobel.task1.feature.product.data.local.ProductDao
import pl.wrobel.task1.feature.product.data.local.model.ProductCached
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.domain.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val jsonDataProvider: LocalJsonDataProvider,
) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> = fromLocal()

    private fun fromLocal(): Flow<List<Product>> = productDao.loadProducts().map { list ->
        if (list.isEmpty()) {
            loadJson()
        } else {
            list.map { it.toProduct() }
        }
    }

    private suspend fun ProductRepositoryImpl.loadJson() =
        jsonDataProvider.getProducts().map { it.toProduct() }.also { saveProducts(it) }

    private suspend fun saveProducts(products: List<Product>) {
        withContext(Dispatchers.IO) { productDao.saveProducts(products.map { ProductCached(it) }) }
    }

    override suspend fun updateProduct(product: Product) = productDao.update(ProductCached(product))
}