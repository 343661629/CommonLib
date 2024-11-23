package com.example.commonlibproject.network

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
object Constants {
    const val BASE_URL = "https://www.mxnzp.com/"

    //业务接口
    const val GET_NEWS_TYPE_LIST = "api/news/types/v2"

    //获取短信验证码
    const val GET_SMS_CODE = "api/cloud_user/code/get"
    //手机号注册
    const val GET_REGISTER = "api/cloud_user/registe"
    //获取新闻类型
    const val GET_NEWS_TYPE = "api/news/types/v2"
    //获取新闻列表
    const val GET_NEWS_LIST = "api/news/list/v2"
    //获取新闻详情
    const val GET_NEWS_DETAIL = "api/news/details/v2"

}