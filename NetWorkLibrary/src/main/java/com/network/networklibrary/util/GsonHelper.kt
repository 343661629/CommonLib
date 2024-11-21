package com.network.networklibrary.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
object GsonHelper {
    private val gson by lazy { Gson() }

    fun toJson(src: Any?): String {
        return src?.let { gson.toJson(it) } ?: ""
    }

    fun toJson(src: Any?, typeOfSrc: Type): String {
        return src?.let { gson.toJson(it, typeOfSrc) } ?: ""
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>): T? {
        if (json.isNullOrEmpty()) return null
        return try {
            gson.fromJson(json, classOfT)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun <T> fromJson(json: String?, type: Type): T? {
        if (json.isNullOrEmpty()) return null
        return try {
            gson.fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun <T> fromJsonToList(json: String?, clazz: Class<Array<T>>): List<T> {
        if (json.isNullOrEmpty()) return emptyList()
        return try {
            val arr = gson.fromJson(json, clazz)
            arr?.toList() ?: emptyList()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            emptyList()
        }
    }


}