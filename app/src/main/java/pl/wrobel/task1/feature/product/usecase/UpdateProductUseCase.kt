package pl.wrobel.task1.feature.product.usecase

import kotlinx.coroutines.CoroutineScope
import pl.wrobel.base.UseCase
import pl.wrobel.task1.feature.product.domain.Product
import pl.wrobel.task1.feature.product.domain.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    UseCase<Unit, Pair<Product, Int>>() {
    override suspend fun action(params: Pair<Product, Int>, scope: CoroutineScope) {
        val product = params.first
        val newQuantity = product.quantity + params.second
        if (newQuantity >= 0) {
            productRepository.updateProduct(product.copy(quantity = newQuantity))
        }
    }
}
