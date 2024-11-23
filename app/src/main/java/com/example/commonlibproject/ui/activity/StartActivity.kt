package com.example.commonlibproject.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.common.commonlib.base.BaseComposeActivity
import com.common.commonlib.util.MMKVUtil
import com.common.commonlib.util.launchOnMain
import com.example.commonlibproject.R
import com.example.commonlibproject.constans.Constants.IS_LOGIN
import com.example.commonlibproject.ui.activity.ui.theme.CommonLibProjectTheme
import com.example.commonlibproject.ui.page.LoginPage
import com.example.commonlibproject.ui.page.registerPage
import com.example.commonlibproject.viewmodel.UserInfoViewModel
import kotlinx.coroutines.delay

/**
 * Author: JiaLin Huang
 * Description:启动页面
 * Date: 2024/11/20
 */
class StartActivity : BaseComposeActivity() {
    //注意，这里的viewModels()是ktx库提供的一个委托属性，用于获取ViewModel实例
    private val userInfoViewModel: UserInfoViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if ((intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish()
            return
        }
        //enableEdgeToEdge()
        setContent {
            CommonLibProjectTheme {
                setContentLayout(this)
            }
        }
    }


    @Composable
    private fun setContentLayout(activity: Activity) {
        val context = LocalContext.current
        val isShowLoginPage = remember { mutableStateOf(false) }
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash"){
                splashLayout()
            }
            composable("login"){
                LoginPage(navController)
            }

            composable("registerPage"){
                registerPage(navController,userInfoViewModel)
            }

        }

        val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
        val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
        Log.d("StartActivity", "======setContentLayout: $lifecycleState")
        LaunchedEffect(key1 = Unit) {
            launchOnMain {
                //延迟2秒跳转
                delay(3000)
                //未登录状态，则跳转到登录页面
                val isLog = MMKVUtil.get(IS_LOGIN, true)
                if (isLog) {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    activity.finish()
                } else {
                    /**
                     * 这段代码的含义是：
                     * 导航到 "login" 目的地。
                     * 弹出导航堆栈中的所有目的地，直到 "splash" 目的地为止。
                     * inclusive = true 表示 "splash" 目的地本身也会被弹出（移除）。
                     * 这样做的结果是，当你导航到 "login" 目的地时，"splash" 以及它之前的所有目的地都会被移除。
                     * 这通常用于在某些情况下清理导航堆栈，例如用户从启动页（splash screen）导航到登录页（login screen）时，
                     * 不希望用户能够通过返回按钮回到启动页。
                     */
                    navController.navigate("login"){
                        popUpTo("splash"){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun splashLayout() {
        Image(
            painter = painterResource(id = R.mipmap.splash),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }








}



