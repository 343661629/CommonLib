package com.example.commonlibproject.model

import androidx.compose.runtime.MutableState

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/18
 */
data class BottomItemModel(
    var name: String,
    var icon: Int,
    var isSelect: MutableState<Boolean>
)
