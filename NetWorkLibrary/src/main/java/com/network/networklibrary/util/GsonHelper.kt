package com.network.networklibrary.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
object GsonHelper {
    val gson = Gson()

    fun Any?.toJson(): String = this?.let { gson.toJson(it) } ?: ""

    fun Any?.toJson(typeOfSrc: Type): String = this?.let { gson.toJson(it, typeOfSrc) } ?: ""

    inline fun <reified T> String?.fromJson(): T? = this?.takeIf { it.isNotEmpty() }?.let {
        try {
            gson.fromJson(it, T::class.java)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun <T> String?.fromJson(type: Type): T? = this?.takeIf { it.isNotEmpty() }?.let {
        try {
            gson.fromJson(it, type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    inline fun <reified T> String?.fromJsonToList(): List<T> = this?.takeIf { it.isNotEmpty() }?.let {
        try {
            gson.fromJson(it, Array<T>::class.java)?.toList() ?: emptyList()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            emptyList()
        }
    } ?: emptyList()
}