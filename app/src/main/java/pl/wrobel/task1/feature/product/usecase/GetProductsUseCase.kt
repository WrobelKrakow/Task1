package pl.wrobel.task1.feature.product.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import pl.wrobel.base.UseCase
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.domain.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    UseCase<Flow<List<Product>>, Unit>() {
    override suspend fun action(params: Unit, scope: CoroutineScope): Flow<List<Product>> = productRepository.getProducts()

}
