package com.network.networklibrary.util

import android.util.Log
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/21
 */

/**
 * 网络情趣处理结果
 */
inline fun <reified T> handleNetworkResult(
    result: Result<String>,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (Throwable) -> Unit
) {
    result.onSuccess { data ->
        Log.i("NetWorkQuest", "data--->$data")
        val parsedData = GsonHelper.fromJson(data, T::class.java)
        parsedData?.let {
            onSuccess(it)
        } ?: run {
            onFailure(Throwable("data is null"))
        }
    }.onFailure { error ->
        Log.e("NetWorkQuest", "error--->${error.message}")
        onFailure(Throwable(error.message))
    }
}


/**
 * 判断字符串是否是json格式
 * @param json
 * @return
 */
fun isJson(json: String): Boolean {
    if (json.isEmpty()) {
        return false
    }
    return try {
        JsonParser().parse(json)
        true
    } catch (e: JsonSyntaxException) {
        false
    } catch (e: JsonParseException) {
        false
    }
}