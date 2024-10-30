package pl.wrobel.task1.feature.product.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.wrobel.task1.feature.product.data.local.model.ProductCached

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductCached")
    fun loadProducts(): Flow<List<ProductCached>>

    @Query("SELECT * FROM ProductCached")
    suspend fun getProducts(): List<ProductCached>

    @Update
    suspend fun update(productCached: ProductCached)

    @Query("SELECT * FROM ProductCached WHERE id IN (:ids)")
    suspend fun loadProducts(ids: List<Int>): List<ProductCached>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<ProductCached>)

    @Query("DELETE FROM ProductCached WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}
