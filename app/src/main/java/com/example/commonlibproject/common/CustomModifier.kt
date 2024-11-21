package com.example.commonlibproject.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.commonlibproject.ui.theme.color_ffffff

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */

fun Modifier.pageBackground(): Modifier {
    return this.fillMaxSize().background(color_ffffff)

}
