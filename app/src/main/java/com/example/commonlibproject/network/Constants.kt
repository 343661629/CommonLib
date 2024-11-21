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
    //帐号密码登录
    const val GET_LOGIN_PWD = "api/cloud_user/login/psw"
    //验证码登录
    const val GET_LOGIN_CODE = "api/cloud_user/login/code"
    //获取用户信息
    const val GET_USER_INFO = "api/cloud_user/userinfo/get"
    //更新用户信息
    const val GET_UPDATE_USER_INFO = "api/cloud_user/userinfo/update"
    //修改密码
    const val GET_UPDATE_PWD = "api/cloud_user/psw/rest"
    //退出登录
    const val GET_LOGIN_OUT = "api/cloud_user/logout"
}