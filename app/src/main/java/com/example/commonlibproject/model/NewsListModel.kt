package com.example.commonlibproject.model

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/23
 */
data class NewsListModel(
    val code: Int,
    val `data`: List<NewListItemModel>,
    val msg: String
)

data class NewListItemModel(
    val digest: String,
    val imgList: Any,
    val newsId: String,
    val postTime: String,
    val source: String,
    val title: String,
    val videoList: Any
)