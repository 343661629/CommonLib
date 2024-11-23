package com.example.commonlibproject.model

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/24
 */
data class NewDetailModel(
    val code: Int,
    val `data`: Detail,
    val msg: String
)

data class Detail(
    val id: Int,
    val items: List<Item>
)

data class Item(
    val content: String,
    val imageUrl: String,
    val type: String,
    val videoUrl: Any
)