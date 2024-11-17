package com.network.networklibrary.http

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Author: Jialin Huang
 * Description:定义请求接口
 * Date: 2024/11/17
 */
interface ApiService {
    /**
     * 以get方式的网络请求接口
     * @param url 可传全路径或者只传baseUrl之后的路径
     * @param map 键值对参数
     */
    @GET("")
    suspend fun get(@Url url: String, @QueryMap map: Map<String, String>): String

    /**
     * 以post方式的网络请求接口
     * @param url 可传全路径或者只传baseUrl之后的路径
     * @param map 键值对参数
     */
    @POST("")
    suspend fun post(@Url url: String, @QueryMap map: Map<String, String>): String

    /**
     * post实体
     */
    @POST("")
    suspend fun postBody(@Url url: String, @Body body: Any): String

    /**
     * post表单
     */
    @FormUrlEncoded
    @POST("")
    suspend fun postField(@Url url: String, @FieldMap map: Map<String, String>): String

    /**
     * 单图上传
     */
    @Multipart
    @POST("")
    suspend fun upLoadFile(@Url url: String, @Part file: MultipartBody.Part): String

    /**
     * 多图上传
     */
    @Multipart
    @POST("")
    suspend fun uploadFiles(
        @Url url: String,
        @Part("filename") description: String,
        @PartMap maps: Map<String, @JvmSuppressWildcards RequestBody>
    ): String

    /**
     * 下载
     */
    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl: String): String
}