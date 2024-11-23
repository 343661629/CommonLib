package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.commonlibproject.R
import com.example.commonlibproject.model.Data
import com.example.commonlibproject.model.Detail
import com.example.commonlibproject.model.Item
import com.example.commonlibproject.viewmodel.NewsVidewModel

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/23
 */


@Composable
fun NewsDetail(
    navController: NavHostController,
    newId: String,
    newViewModel: NewsVidewModel = viewModel()
) {
    val detailResult by newViewModel.newsDetailState.collectAsState()
    val detailResultState = remember {
        mutableStateListOf<Item>()
    }



    LaunchedEffect(key1 = detailResult) {
        detailResult?.takeIf { it.items.isNotEmpty() }?.let {
            detailResultState.clear()
            detailResultState.addAll(it.items)
        }
    }



    /**
     * 获取局部组件的生命周期
     */
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifecycle) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    newViewModel.getNewsDetail(newId)
                    Log.i("NewsPage", "ON_CREATE")
                }

                Lifecycle.Event.ON_START -> {
                    Log.i("NewsPage", "ON_START")
                }

                Lifecycle.Event.ON_RESUME -> {
                    Log.i("NewsPage", "ON_RESUME")
                }

                Lifecycle.Event.ON_PAUSE -> {
                    Log.i("NewsPage", "ON_PAUSE")
                }

                Lifecycle.Event.ON_STOP -> {
                    Log.i("NewsPage", "ON_STOP")
                }

                Lifecycle.Event.ON_DESTROY -> {
                    Log.i("NewsPage", "ON_DESTROY")
                }

                else -> throw IllegalStateException()
            }
        }
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    Column {
        Box(modifier = Modifier.fillMaxWidth().height(55.dp)){
            customTitleBar(
                titleStr = "News Detail",
                callBack = {
                    navController.popBackStack()
                },
            )
        }

        LazyColumn {
            itemsIndexed(detailResultState) { index, item ->
                when (item.type) {
                    "img" -> {
                        ImageItem(item.imageUrl)
                    }
                    "video" -> {
                        //VideoItem(item.videoUrl.toString())
                    }
                    "text" -> {
                        TextItem(item.content)
                    }
                }
            }
        }
    }




}

@Composable
fun TextItem(content: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, end = 15.dp), contentAlignment = Alignment.Center) {
        Text(text = content, style = TextStyle(fontSize = 16.sp, color = colorResource(id = R.color.black)))
    }
}




@Composable
fun ImageItem(imageUrl: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, end = 15.dp), contentAlignment = Alignment.Center) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(false)
                .build(),
            placeholder = painterResource(R.mipmap.ic_test),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .clip(shape = RoundedCornerShape(5.dp))
        )
    }
}

