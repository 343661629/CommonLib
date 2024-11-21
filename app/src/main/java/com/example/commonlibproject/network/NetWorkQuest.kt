package com.example.commonlibproject.network

import android.util.Log
import com.common.commonlib.util.launchOnMain
import com.network.networklibrary.http.IConfig
import com.network.networklibrary.http.NetWorkConfig
import com.network.networklibrary.util.GsonHelper
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
    val netWorkConfig by lazy { NetWorkConfig() }
    private val headerParam = HashMap<String, String>()


    /**
     * 获取所有新闻类型列表
     */
    inline fun <reified T> getNewsTypeList(type: Int, crossinline result: (Result<T>) -> Unit) {
        val param = commonParam()
        launchOnMain {
            netWorkConfig.get(Constants.GET_NEWS_TYPE_LIST, param, this@NetWorkQuest).collect {
                it.onSuccess { data ->
                    Log.i("NewVideModel", "data--->$data")
                    val parsedData = GsonHelper.fromJson(data, T::class.java)
                    parsedData?.let {
                        result(Result.success(parsedData))
                    } ?: run {
                        result(Result.failure(Throwable("data is null")))
                    }
                }.onFailure { error ->
                    // 处理错误
                    Log.e("NewVideModel", "error--->${error.message}")
                    result(Result.failure(Throwable(error.message)))
                }
            }
        }
    }


    /**
     * 获取验证码
     * @param phoneNumber 手机号
     * @param type 类型，0 获取注册验证码 1 修改密码验证码 2 验证码登录发送验证码
     */
    fun getSMSCode(phoneNumber: String, type: Int): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_SMS_CODE, param, this)
    }


    /**
     * 手机号注册
     */
    fun getRegister(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_REGISTER, param, this)
    }


    /**
     * 帐号密码登录
     */
    fun getLoginPwd(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_LOGIN_PWD, param, this)
    }

    /**
     * 验证吗登录
     */
    fun getLoginCode(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_LOGIN_CODE, param, this)
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_USER_INFO, param, this)
    }

    /**
     * 更新用户信息
     */
    fun getUpDateUserInfo(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_UPDATE_USER_INFO, param, this)
    }

    /**
     * 修改密码
     */
    fun getUpDatePwd(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_UPDATE_PWD, param, this)
    }

    /**
     * 退出登录
     */
    fun getLoginOut(): Flow<Result<String>> {
        val param = commonParam()
        return netWorkConfig.get(Constants.GET_LOGIN_OUT, param, this)
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


    override fun commonParam(): MutableMap<String, String> {
        val commonParam = mutableMapOf<String, String>()
        commonParam["app_id"] = "scvkymfqjaogwgmc"
        commonParam["app_secret"] = "8tdgQagZF9JY4YRbzCghaz5uN3BZYjAF"
        return commonParam
    }


}