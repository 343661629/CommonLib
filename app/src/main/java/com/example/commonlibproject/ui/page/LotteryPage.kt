package com.example.commonlibproject.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.commonlibproject.ui.theme.color_45a7ff

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/19
 */

@Composable
fun LotteryPage() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = color_45a7ff)) {
        Text(text = "Lottery Page")
    }
}