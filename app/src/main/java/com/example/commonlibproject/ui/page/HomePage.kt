package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import com.example.commonlibproject.R

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/19
 */

@Composable
fun HomePage() {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("http://cms-bucket.ws.126.net/2024/1123/19f977c4p00sneerp000bc0009c0070c.png")
            .crossfade(false)
            .build(),
        placeholder = painterResource(R.mipmap.ic_test),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
    )
}





