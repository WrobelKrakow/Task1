package pl.wrobel.task1.feature.product.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wrobel.task1.feature.product.data.repository.ProductRepositoryImpl
import pl.wrobel.task1.feature.product.domain.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Bindings {

        @Singleton
        @Binds
        abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository
    }
}
