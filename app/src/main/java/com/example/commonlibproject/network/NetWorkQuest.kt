package com.example.commonlibproject.network

import android.util.Log
import com.common.commonlib.util.launchOnMain
import com.network.networklibrary.http.IConfig
import com.network.networklibrary.http.NetWorkConfig
import com.network.networklibrary.util.GsonHelper
import com.network.networklibrary.util.handleNetworkResult
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
     * 获取验证码
     * @param phone 手机号
     * @param type 类型，0 获取注册验证码 1 修改密码验证码 2 验证码登录发送验证码
     */
    inline fun <reified T> getSMSCode(
        type: Int,
        phone: String,
        crossinline result: (Result<T>) -> Unit
    ) {
        val param = commonParam()
        param["phone"] = phone
        param["type"] = type.toString()
        launchOnMain {
            netWorkConfig.get(Constants.GET_SMS_CODE, param, this@NetWorkQuest).collect { result ->
                handleNetworkResult<T>(result,
                    onSuccess = { parsedData ->
                        result(Result.success(parsedData))
                    },
                    onFailure = { error ->
                        result(Result.failure(error))
                    }
                )
            }
        }
    }


    /**
     * 手机号注册
     */
    inline fun <reified T> register(
        phone: String,
        code: String,
        nickname: String,
        password: String,
        sex: Int,
        birthday: String = "1999-08-09",
        email: String = "12345678@qq.com",
        extra: String = "",
        crossinline result: (Result<T>) -> Unit
    ) {
        val param = commonParam()
        param["phone"] = phone
        param["code"] = code
        param["nickname"] = nickname
        param["password"] = password
        param["sex"] = sex.toString()
        param["birthday"] = birthday
        param["email"] = email
        param["extra"] = extra

        launchOnMain {
            netWorkConfig.get(Constants.GET_REGISTER, param, this@NetWorkQuest).collect { result ->
                handleNetworkResult<T>(result,
                    onSuccess = { parsedData ->
                        result(Result.success(parsedData))
                    },
                    onFailure = { error ->
                        result(Result.failure(error))
                    }
                )
            }
        }
    }


    /**
     * 获取新闻类型
     */
    inline fun <reified T> getNewType(
        crossinline result: (Result<T>) -> Unit
    ) {
        val param = commonParam()
        launchOnMain {
            netWorkConfig.get(Constants.GET_NEWS_TYPE, param, this@NetWorkQuest).collect { result ->
                handleNetworkResult<T>(result,
                    onSuccess = { parsedData ->
                        result(Result.success(parsedData))
                    },
                    onFailure = { error ->
                        result(Result.failure(error))
                    }
                )
            }
        }
    }

    /**
     * 获取新闻列表
     */
    inline fun <reified T> getNewsList(
        typeId: String,
        page: String,
        crossinline result: (Result<T>) -> Unit
    ) {
        val param = commonParam()
        param["typeId"] = typeId
        param["page"] = page
        launchOnMain {
            netWorkConfig.get(Constants.GET_NEWS_LIST, param, this@NetWorkQuest).collect { result ->
                handleNetworkResult<T>(result,
                    onSuccess = { parsedData ->
                        result(Result.success(parsedData))
                    },
                    onFailure = { error ->
                        result(Result.failure(error))
                    }
                )
            }
        }
    }



    /**
     * 获取新闻详情
     */
    inline fun <reified T> getNewDetail(
        newsId:String,
        crossinline result: (Result<T>) -> Unit
    ) {
        val param = commonParam()
        param["newsId"] = newsId
        launchOnMain {
            netWorkConfig.get(Constants.GET_NEWS_DETAIL, param, this@NetWorkQuest).collect { result ->
                handleNetworkResult<T>(result,
                    onSuccess = { parsedData ->
                        result(Result.success(parsedData))
                    },
                    onFailure = { error ->
                        result(Result.failure(error))
                    }
                )
            }
        }
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