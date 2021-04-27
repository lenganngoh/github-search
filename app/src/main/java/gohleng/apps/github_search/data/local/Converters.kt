package gohleng.apps.github_search.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import gohleng.apps.github_search.data.model.Owner

class Converters {
    companion object {
        val gson = Gson()
    }

    @TypeConverter
    fun ownerJson(value: Owner?): String {
        return if (value != null) {
            gson.toJson(value)
        } else {
            ""
        }
    }

    @TypeConverter
    fun parseOwner(value: String): Owner =
        gson.fromJson(value, Owner::class.java)
}