package pl.wrobel.task1.core.database

import androidx.room.TypeConverter
import pl.wrobel.base.data.fromJson
import pl.wrobel.base.data.toJson

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toJsonListString(data: List<String>): String = data.toJson()

        @TypeConverter
        @JvmStatic
        fun toListString(json: String): List<String> = json.fromJson()

        @TypeConverter
        @JvmStatic
        fun toJsonListBoolean(data: List<Boolean>): String = data.toJson()

        @TypeConverter
        @JvmStatic
        fun toListBoolean(json: String): List<Boolean> = json.fromJson()

        @TypeConverter
        @JvmStatic
        fun toJsonListInt(data: List<Int>): String = data.toJson()

        @TypeConverter
        @JvmStatic
        fun toListIds(json: String): List<Int> = json.fromJson()

        @TypeConverter
        @JvmStatic
        fun toJson(data: Map<String, Double>): String = data.toJson()

        @TypeConverter
        @JvmStatic
        fun fromJsonToMap(json: String): Map<String, Double> = json.fromJson()
    }
}
