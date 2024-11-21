package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.commonlibproject.R
import com.example.commonlibproject.common.pageBackground

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prePage() {
    //LoginPage()
}

@Composable
fun LoginPage(navController: NavHostController) {
    var userNameStr by remember{ mutableStateOf("") }
    var passCodeStr by remember{ mutableStateOf("") }
    Box(modifier = Modifier.pageBackground()) {
        ConstraintLayout {
            val (titleBar, userName, passCode, loginBtn) = createRefs()
            Box(modifier = Modifier.constrainAs(titleBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }) {
                customTitleBar(
                    isNeedBack = false,
                    titleStr = "Login",
                    rightText = "Register",
                    callBack = {
                        Log.e("LoginPage", "back")
                        navController.popBackStack()
                    },
                    rightCallBack = {
                        navController.navigate("registerPage")
                    })
            }

            Box(modifier = Modifier
                .constrainAs(userName) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(titleBar.bottom)
                }
                .padding(top = 100.dp)) {
                textFieldLayout(
                    false,
                    hint = "please input user name",
                    leadingIcon = R.mipmap.yonghuming,
                    maxLength = 15,
                    onValueChange = {
                        userNameStr = it
                        Log.e("LoginPage", "onValueChange:$it")
                    })
            }

            Box(modifier = Modifier
                .constrainAs(passCode) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(userName.bottom)
                }
                .padding(top = 30.dp)) {
                textFieldLayout(
                    true,
                    hint = "please input passcode",
                    leadingIcon = R.mipmap.mima,
                    maxLength = 20,
                    onValueChange = {
                        passCodeStr = it
                        Log.e("LoginPage", "onValueChange:$it")
                    })
            }

            Box(modifier = Modifier
                .constrainAs(loginBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(passCode.bottom)
                }
                .padding(start = 60.dp, end = 60.dp, top = 60.dp)) {
                CustomButton(text = "Login", onClick = {
                    Log.e("LoginPage", "Login")
                }, modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth())
            }

        }
    }
}