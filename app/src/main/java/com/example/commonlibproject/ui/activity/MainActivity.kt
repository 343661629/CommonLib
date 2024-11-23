package com.example.commonlibproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.commonlibproject.R
import com.example.commonlibproject.model.BottomItemModel
import com.example.commonlibproject.ui.page.HomePage
import com.example.commonlibproject.ui.page.LotteryPage
import com.example.commonlibproject.ui.page.NewsDetail
import com.example.commonlibproject.ui.page.NewsPage
import com.example.commonlibproject.ui.page.StoryPage
import com.example.commonlibproject.ui.page.bottomNavigation
import com.example.commonlibproject.ui.theme.CommonLibProjectTheme

/**
 * Author: JiaLin Huang
 * Description:主页面
 * Date: 2024/11/18
 */
class MainActivity : ComponentActivity() {
    //对于集合中的数据，我们使用mutableStateListOf来进行包装，这样可以保证数据的变化能够被Compose感知到
    private val items = mutableStateListOf(
        BottomItemModel("home", R.mipmap.shouyeweixuanzhong, mutableStateOf(true)),
        BottomItemModel("news", R.mipmap.xinwenweixuanzhong, mutableStateOf(false)),
        BottomItemModel("story", R.mipmap.gushixuanzhong, mutableStateOf(false)),
        BottomItemModel("lottery", R.mipmap.caipiaoxuanzhong, mutableStateOf(false)),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommonLibProjectTheme {
                setContentLayout()
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    @Preview(showBackground = true)
    @Composable
    fun setContentLayout() {
        val itemsState = remember { items }
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (page, bottomNavigation) = createRefs()
            val navController = rememberNavController()
            Box(modifier = Modifier.constrainAs(page) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(bottomNavigation.top)
                height = Dimension.fillToConstraints
            }) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomePage() }
                    composable("news") { NewsPage(navController) }
                    composable("story") { StoryPage() }
                    composable("lottery") { LotteryPage() }
                    composable(
                        "newsDetail/{newsId}",
                        arguments = mutableListOf(navArgument("newsId") {
                            type = NavType.StringType
                        })
                    ) { it ->
                        NewsDetail(navController, newId = it.arguments?.getString("newsId") ?: "")
                    }
                }
            }
            Log.d("MainActivity", "onCreate: ")
            Box(modifier = Modifier.constrainAs(bottomNavigation) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
                bottomNavigation(itemsState, onClickItem = { index ->
                    itemsState.forEachIndexed { i, item ->
                        item.isSelect.value = i == index
                    }
                    val selectedItem = itemsState[index]
                    navController.navigate(selectedItem.name) {
                        popUpTo(itemsState[index].name) { inclusive = true }
                    }
                })
            }
        }
    }
}

