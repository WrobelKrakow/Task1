package pl.wrobel.base.data

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader

inline fun <reified T> getListFromJson(context: Context, fileName: String): List<T> = Gson().fromJson(
    InputStreamReader(context.assets.open(fileName)),
    arrayOf<T>()::class.java,
).toList()