package com.example.commonlibproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.commonlibproject.ui.page.bottomNavigation
import com.example.commonlibproject.ui.page.customTitleBar
import com.example.commonlibproject.ui.theme.CommonLibProjectTheme
import com.example.commonlibproject.viewmodel.NewVideModel

/**
 * Author: JiaLin Huang
 * Description:主页面
 * Date: 2024/11/18
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommonLibProjectTheme {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (title, bottomNavigation) = createRefs()
                    Box {
                        customTitleBar()
                    }

                    Box(modifier = Modifier.constrainAs(bottomNavigation) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {
                        bottomNavigation()
                    }


                }

            }
        }
    }
}

