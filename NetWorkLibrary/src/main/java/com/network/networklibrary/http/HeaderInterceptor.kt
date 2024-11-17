package com.network.networklibrary.http

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Author: Jialin Huang
 * Description: 头部拦截器
 * Date: 2024/11/17
 */
class HeaderInterceptor(private val headMap: Map<String, String>?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        if (url.isEmpty()) {
            return chain.proceed(request)
        }

        val builder = request.newBuilder()
        headMap?.forEach { (key, value) ->
            builder.addHeader(key, value)
        }

        return try {
            chain.proceed(builder.build())
        } catch (e: SocketTimeoutException) {
            e.localizedMessage
            return chain.proceed(request)
        }
    }
}