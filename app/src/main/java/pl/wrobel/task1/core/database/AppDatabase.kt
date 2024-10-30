package pl.wrobel.task1.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.wrobel.task1.BuildConfig
import pl.wrobel.task1.feature.product.data.local.ProductDao
import pl.wrobel.task1.feature.product.data.local.model.ProductCached

@Database(
    entities = [ProductCached::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = init(context)
                INSTANCE = instance
                instance
            }
        }

        private fun init(context: Context): AppDatabase {
            val databaseBuilder = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Database")
            if (BuildConfig.DEBUG) databaseBuilder.fallbackToDestructiveMigration()
            return databaseBuilder.build()
        }
    }

    abstract fun productDao(): ProductDao
}
