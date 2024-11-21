package com.example.commonlibproject.model

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */
data class LoginModel(
    val code: Int,
    val `data`: Model,
    val msg: String
)

data class Model(
    val token: String,
    val tokenExpire: Long,
    val userId: String
)