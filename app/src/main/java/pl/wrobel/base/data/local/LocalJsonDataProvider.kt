package pl.wrobel.base.data.local

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.wrobel.task1.feature.product.data.remote.model.ProductRemote
import java.io.InputStreamReader
import javax.inject.Inject

class LocalJsonDataProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getProducts(): List<ProductRemote> = getListFromJson(context, ITEMS_JSON)

    companion object {
        private const val ITEMS_JSON = "items.json"

    }
}

inline fun <reified T> getListFromJson(context: Context, fileName: String): List<T> = Gson().fromJson(
    InputStreamReader(context.assets.open(fileName)),
    arrayOf<T>()::class.java,
).toList()