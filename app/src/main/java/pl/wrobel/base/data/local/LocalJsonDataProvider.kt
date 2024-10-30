package pl.wrobel.base.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.wrobel.base.data.getListFromJson
import pl.wrobel.task1.feature.product.data.remote.model.ProductRemote
import javax.inject.Inject

class LocalJsonDataProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getProducts(): List<ProductRemote> = getListFromJson(context, ITEMS_JSON)

    companion object {
        private const val ITEMS_JSON = "items.json"

    }
}
