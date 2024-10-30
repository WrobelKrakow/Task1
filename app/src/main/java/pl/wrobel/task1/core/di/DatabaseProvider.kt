package pl.wrobel.task1.core.di;

import android.app.Application
import dagger.Module;
import dagger.Provides
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import pl.wrobel.task1.core.database.AppDatabase
import pl.wrobel.task1.feature.product.data.local.ProductDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseProvider {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase = AppDatabase.getDatabase(app)

    @Singleton
    @Provides
    fun productDao(db: AppDatabase): ProductDao = db.productDao()
}
