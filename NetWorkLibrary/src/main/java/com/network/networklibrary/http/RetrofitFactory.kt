package com.network.networklibrary.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
class RetrofitFactory constructor(builder: RetrofitBuilder) {

    companion object {
        const val REQUEST_TIMEOUT = 15L
    }

    private val mHeadMap: MutableMap<String, String> = builder.headers.toMutableMap()
    private val headerInterceptor: HeaderInterceptor = HeaderInterceptor(mHeadMap)
    private val baseUrl: String? = builder.baseUrl
    private val socketFactory: SSLSocketFactory? = builder.socketFactory
    private val trustManager: X509TrustManager? = builder.trustManager
    private val hostnameVerifier: HostnameVerifier? = builder.hostnameVerifier

    /**
     * retrofit配置
     */
    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(LogInterceptor())
        addInterceptor(headerInterceptor)
        connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        socketFactory?.let {
            sslSocketFactory(it, trustManager!!)
            hostnameVerifier(hostnameVerifier!!)
        }
    }.build()

    /**
     * 获取接口服务对象
     */
    fun getApiService(): ApiService = getRetrofit().create(ApiService::class.java)


}