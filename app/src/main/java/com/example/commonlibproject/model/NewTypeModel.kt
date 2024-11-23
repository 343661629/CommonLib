package com.example.commonlibproject.model

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/21
 */
data class NewTypeModel(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)

data class Data(
    var isSelectPosition:Boolean = false,
    val typeId: Int,
    val typeName: String
)