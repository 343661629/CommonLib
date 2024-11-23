package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.common.commonlib.util.launchOnMain
import com.example.commonlibproject.R
import com.example.commonlibproject.model.Data
import com.example.commonlibproject.model.NewListItemModel
import com.example.commonlibproject.viewmodel.NewsVidewModel
import com.network.networklibrary.util.GsonHelper
import kotlinx.coroutines.delay

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/19
 */

@Composable
fun NewsPage(navController:NavHostController, newViewModel: NewsVidewModel = viewModel()) {
    val newsTypeResult by newViewModel.newsTypeResult.collectAsState()
    val newsTypeListState = remember { mutableStateListOf<Data>() }

    val newsListResult by newViewModel.newsListState.collectAsState()
    val newsListResultState = remember { mutableStateListOf<NewListItemModel>() }

    Log.e("NewsPage", "newsListResult--->${GsonHelper.toJson(newsListResultState)}")

    LaunchedEffect(key1 = newsTypeResult) {
        Log.e("NewsPage", "newsTypeResult--->${newsTypeResult}")
        newsTypeResult?.data?.takeIf { it.isNotEmpty() }?.let {
            newsTypeListState.clear()
            newsTypeListState.addAll(it)
            launchOnMain {
                delay(1000)
                //获取新闻列表
                newViewModel.getNewsList(newsTypeListState[0].typeId.toString(), "1")
            }
        } ?: run {
            Log.e("NewsPage", "newsTypeResult--->null")
        }
    }

    /**
     * 新闻列表
     */
    LaunchedEffect(key1 = newsListResult) {
        newsListResult.takeIf { it.isNotEmpty() }?.let {
            newsListResultState.clear()
            newsListResultState.addAll(it)
        }
    }


    /**
     * 获取局部组件的生命周期
     */
    val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifecycle) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    newViewModel.getNewType()
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

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            customTitleBar(isNeedBack = false, titleStr = "News", titleColor = R.color.white)
        }
        getNewTypeLayout(newsTypeListState, onClickItem = { item ->
            newsTypeListState.forEach {
                it.isSelectPosition = it.typeId == item.typeId
            }
            newViewModel.getNewsList(item.typeId.toString(), "1")
        })
        getNewsListLayout(newsListResultState, onItemNewsListItem = {
            navController.navigate("newsDetail/${it.newsId}")
        })
    }
}


/**
 * 新闻列表
 */
@Composable
fun getNewsListLayout(
    newsListState: MutableList<NewListItemModel>,
    onItemNewsListItem: (item: NewListItemModel) -> Unit
) {
    LazyColumn(modifier = Modifier.clickable { }) {
        itemsIndexed(newsListState) { _, item ->
            Box(
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 10.dp,
                        bottom = 5.dp
                    )
                    .background(
                        colorResource(id = R.color.mega_container_secondary),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable {
                        onItemNewsListItem(item)
                    }
            ) {

                Log.e("NewsPage", "item.imgList--->${item.imgList}")
                Column (modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)){
                    Row {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://pics2.baidu.com/feed/962bd40735fae6cd8103eed5db6baa2d43a70f35.jpeg?token=4ccc075219cfe28d8cd98b4cbaaffba6")
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.mipmap.ic_test),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .size(100.dp),
                        )

                        Column (modifier = Modifier.padding(start = 10.dp)){
                            Text(
                                text = item.title ?: "", style = TextStyle(
                                    fontSize = 18.sp, color = colorResource(
                                        id = R.color.black
                                    )
                                )
                            )

                            Text(
                                text = item.digest ?: "", style = TextStyle(
                                    fontSize = 15.sp, color = colorResource(
                                        id = R.color.mega_wheel_text_center
                                    )
                                ),
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.source ?: "", style = TextStyle(
                                fontSize = 12.sp, color = colorResource(
                                    id = R.color.color_real_time_vide_heart_sleep
                                )
                            )
                        )

                        Text(
                            text = item.postTime ?: "", style = TextStyle(
                                fontSize = 12.sp, color = colorResource(
                                    id = R.color.color_real_time_vide_heart_sleep
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}


/**
 * 新闻类型列表
 */
@Composable
fun getNewTypeLayout(newsTypeListState: MutableList<Data>, onClickItem: (item: Data) -> Unit) {
    LazyRow {
        itemsIndexed(newsTypeListState) { _, item ->
            Log.e("NewsPage", "item--->${item.typeName}")
            Box(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .border(
                        width = 1.dp,
                        color = if(item.isSelectPosition) colorResource(id = R.color.mega_brand1) else colorResource(id = R.color.white),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable {
                        onClickItem(item)
                    }
            ) {
                Text(
                    text = item.typeName,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                )
            }
        }
    }
}