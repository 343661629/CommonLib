package com.network.networklibrary.http

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Author: Jialin Huang
 * Description:通过builder设计模式将头部信息传递进来
 * Date: 2024/11/17
 */
class RetrofitBuilder {
    var headers: MutableMap<String, String> = mutableMapOf()
    var baseUrl: String? = null
    var socketFactory: SSLSocketFactory? = null
    var trustManager: X509TrustManager? = null
    var hostnameVerifier: HostnameVerifier? = null

    fun setHeaders(headers: MutableMap<String, String>) = apply {
        this.headers = headers
    }

    fun setBaseUrl(baseUrl: String?) = apply {
        this.baseUrl = baseUrl
    }

    fun setSSLSocketFactory(
        sslSocketFactory: SSLSocketFactory?,
        trustManager: X509TrustManager?,
        hostnameVerifier: HostnameVerifier?
    ) = apply {
        this.socketFactory = sslSocketFactory
        this.trustManager = trustManager
        this.hostnameVerifier = hostnameVerifier
    }

    fun build(): RetrofitFactory {
        return RetrofitFactory(this)
    }


}