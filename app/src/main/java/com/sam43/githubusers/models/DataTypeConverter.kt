package com.sam43.githubusers.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * This class will be need when we nee to save
 * List of custom objects into our Room Database
 * Room DB (actually SQLite) don't understand other
 * types rather than the primitive types or know types
 * So we will be needed to convert the type to String (json string)
 * using Gson() to save that object into DB.
 */
object DataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<GithubUser> {
        if (data == null) {
            return emptyList()
        }
        val listType =
            object : TypeToken<List<GithubUser?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun ListToString(owner: Owner?): String {
        return gson.toJson(owner)
    }
}