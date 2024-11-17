package com.network.networklibrary.http

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * Author: Jialin Huang
 * Description:主要是装置请求方式给外部进行调用
 * Date: 2024/11/17
 */
class NetWorkConfig {
    companion object {
        const val TAG = "NetWorkConfig"
    }

    /**
     * get请求模式
     */
    fun get(url: String, param: Map<String, String>, config: IConfig): Flow<Result<String>> =
        flow {
            val response = getRetrofitFactory(config).getApiService().get(url, param)
            emit(Result.success(response))
        }.catch { e ->
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)

    /**
     * post请求模式
     */
    fun post(url: String, param: Map<String, String>, config: IConfig): Flow<Result<String>> =
        flow {
            val response = getRetrofitFactory(config).getApiService().post(url, param)
            emit(Result.success(response))
        }.catch { e ->
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)

    /**
     * post请求模式  参数body形式
     */
    fun postBody(url: String, body: Any, config: IConfig): Flow<Result<String>> =
        flow {
            val response = getRetrofitFactory(config).getApiService().postBody(url, body)
            emit(Result.success(response))
        }.catch { e ->
            Log.e(TAG, "error--->${e.message}")
        }.flowOn(Dispatchers.IO)

    /**
     * 图片上传   单图上传  未测试
     */
    fun upLoadFile(url: String, filePath: String, config: IConfig): Flow<Result<String>> =
        flow {
            val response = getRetrofitFactory(config).getApiService()
                .upLoadFile(url, getMultipartBodyPart(filePath))
            emit(Result.success(response))
        }.catch { e ->
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)

    /**
     * 图片下载  未测试
     */
    fun downLoadFile(url: String, config: IConfig): Flow<Result<String>> =
        flow {
            val response = getRetrofitFactory(config).getApiService().downloadFile(url)
            emit(Result.success(response))
        }.catch { e ->
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)

    /**
     * 文件上传
     */
    private fun getMultipartBodyPart(mPath: String): MultipartBody.Part {
        val file = File(mPath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }

    /**
     * 文件上传
     */
    private fun getMultipartBodyPart(file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }

    /**
     * 获取RetrofitFactory,通过获取到RetrofitFactory，从而获取到apiservice对象
     */
    private fun getRetrofitFactory(config: IConfig): RetrofitFactory {
        val baseUrl = config.addBaseUrl()
        val headerMap = config.addHeader()
        val sslSocketFactory = config.sslSocketFactory()
        val trustManager = config.x509TrustManager()
        val hostnameVerifier = config.hostNameVerifier()
        Log.e(TAG, "baseUrl--->$baseUrl    headerMap--->${headerMap.size}")
        return RetrofitBuilder()
            .setHeaders(headerMap)
            .setBaseUrl(baseUrl)
            .setSSLSocketFactory(sslSocketFactory, trustManager, hostnameVerifier)
            .build()
    }


}