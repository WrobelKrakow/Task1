package pl.wrobel.task1.feature.product.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import pl.wrobel.base.UseCase
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.domain.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    UseCase<Unit, Product>() {
    override suspend fun action(params: Product, scope: CoroutineScope): Unit = productRepository.updateProduct(params)
}
