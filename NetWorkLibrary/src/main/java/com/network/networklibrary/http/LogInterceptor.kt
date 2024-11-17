package com.network.networklibrary.http

import android.util.Log
import com.network.networklibrary.util.NetWorkUtils
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
class LogInterceptor : Interceptor {
    companion object{
        const val TAG = "LogInterceptor"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val headers = request.headers
        val headerBuffer = headers.names().joinToString(", ") { "$it: ${headers[it]}" }

        try {
            Log.e(TAG,"[url = $url ] request header：[$headerBuffer]")
            Log.e(TAG, "---request url:  $url")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val t1 = System.nanoTime()
        Log.e(TAG,"[url = $url]")

        request.body?.let { requestBody ->
            val sb = StringBuilder("[url = $url] request Body[")
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            val charset = requestBody.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8

            if (isPlaintext(buffer)) {
                sb.append(buffer.readString(charset))
            } else {
                sb.append(" (Content-Type = ${requestBody.contentType()}, binary ${requestBody.contentLength()}-byte body omitted)")
            }
            sb.append("]")
        }

        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        val body = response.body ?: return response
        val source = body.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        val charset = body.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8
        val bodyString = buffer.clone().readString(charset)

        if (NetWorkUtils.isJson(bodyString)) {
            Log.i(TAG,bodyString)
        } else {
            Log.e(TAG,"[url = $url ] body：[$bodyString]")
        }

        return response
    }

    private fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0 until 16) {
                if (prefix.exhausted()) break
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}