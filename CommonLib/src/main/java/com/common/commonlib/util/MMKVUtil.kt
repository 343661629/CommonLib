package com.common.commonlib.util

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * Author: Jialin Huang
 * Description:存储工具类
 * Date: 2024/11/20
 */
object MMKVUtil {
    private lateinit var kv: MMKV

    fun initialize(context: Context) {
        MMKV.initialize(context)
        kv = MMKV.defaultMMKV()
    }

    fun <T> put(key: String, value: T) {
        when (value) {
            is String -> kv.putString(key, value)
            is Int -> kv.putInt(key, value)
            is Boolean -> kv.putBoolean(key, value)
            is Long -> kv.putLong(key, value)
            is Float -> kv.putFloat(key, value)
            is Double -> kv.encode(key, value)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }


    fun <T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> kv.getString(key, defaultValue) as T
            is Int -> kv.getInt(key, defaultValue) as T
            is Boolean -> kv.getBoolean(key, defaultValue) as T
            is Long -> kv.getLong(key, defaultValue) as T
            is Float -> kv.getFloat(key, defaultValue) as T
            is Double -> kv.decodeDouble(key, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }


    fun removeKey(key: String) {
        kv.removeValueForKey(key)
    }


    fun clearAll() {
        kv.clearAll()
    }

}