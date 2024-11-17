package com.network.networklibrary.util

import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
object NetWorkUtils {
    /**
     * 判断字符串是否是json格式
     * @param json
     * @return
     */
    fun isJson(json:String): Boolean {
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


}