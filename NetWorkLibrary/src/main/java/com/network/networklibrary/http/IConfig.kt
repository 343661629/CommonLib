package com.network.networklibrary.http

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Author: Jialin Huang
 * Description:对外提供接口来给使用者提供配置数据
 * Date: 2024/11/17
 */
interface IConfig {
    //base URL
    fun addBaseUrl(): String

    //增加头部header
    fun addHeader(): HashMap<String, String>

    //对于请求使用SSL加密证书的，则需要实现该方法
    fun sslSocketFactory(): SSLSocketFactory?

    fun hostNameVerifier(): HostnameVerifier?

    fun x509TrustManager(): X509TrustManager?

    fun commonParam():MutableMap<String,String>


}