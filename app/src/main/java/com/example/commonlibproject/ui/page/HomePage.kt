package com.example.commonlibproject.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.commonlibproject.R

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/19
 */

@Composable
fun HomePage() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .background(
            color = colorResource(
                id = R.color.mega_brand1
            )
        )) {
        Text(text = "Home page")
    }
}





