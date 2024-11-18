package com.example.commonlibproject.network

import com.network.networklibrary.http.IConfig
import com.network.networklibrary.http.NetWorkConfig
import kotlinx.coroutines.flow.Flow
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
class NetWorkQuest : IConfig {
    private val netWorkConfig by lazy { NetWorkConfig() }
    private val headerParam = HashMap<String, String>()


    /**
     * 获取所有新闻类型列表
     */
    fun getNewsTypeList(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_NEWS_TYPE_LIST, param, this)
    }


    override fun addBaseUrl(): String {
        return Constants.BASE_URL
    }

    override fun addHeader(): HashMap<String, String> {
        return headerParam
    }

    override fun sslSocketFactory(): SSLSocketFactory? {
        return null
    }

    override fun hostNameVerifier(): HostnameVerifier? {
        return null
    }

    override fun x509TrustManager(): X509TrustManager? {
        return null
    }


    override fun commonParam():MutableMap<String,String>{
        val commonParam = mutableMapOf<String,String>()
        commonParam["app_id"] = "scvkymfqjaogwgmc"
        commonParam["app_secret"] = "8tdgQagZF9JY4YRbzCghaz5uN3BZYjAF"
        return commonParam
    }


}