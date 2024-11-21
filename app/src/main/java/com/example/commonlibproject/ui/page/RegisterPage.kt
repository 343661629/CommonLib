package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.commonlibproject.R
import com.example.commonlibproject.common.pageBackground

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */

@Composable
fun registerPage(navController: NavHostController) {
    var userNameStr by remember { mutableStateOf("") }
    var phoneNumberStr by remember { mutableStateOf("") }
    var phoneSMsCodeStr by remember { mutableStateOf("") }
    var passCodeStr by remember { mutableStateOf("") }
    val options = mutableListOf("male", "female")
    var selectoptions by remember {
        mutableStateOf(options[0])
    }
    Box(modifier = Modifier.pageBackground()) {
        ConstraintLayout {
            val (titleBar, userName, phoneNumber, smsCode, passCode, sex, loginBtn) = createRefs()
            Box(modifier = Modifier.constrainAs(titleBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }) {
                customTitleBar(
                    isNeedBack = false,
                    titleStr = "Register",
                    callBack = {
                        Log.e("Register", "back")
                        navController.popBackStack()
                    },
                )
            }

            //用户名
            Box(modifier = Modifier
                .constrainAs(userName) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(titleBar.bottom)
                }
                .padding(top = 100.dp)) {
                textFieldLayout(
                    false,
                    hint = "user name",
                    leadingIcon = R.mipmap.yonghuming,
                    maxLength = 15,
                    onValueChange = {
                        userNameStr = it
                        Log.e("Register", "onValueChange:$it")
                    })
            }

            //手机号
            Box(modifier = Modifier
                .constrainAs(phoneNumber) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(userName.bottom)
                }
                .padding(top = 30.dp)) {
                textFieldLayout(
                    false,
                    hint = "phone number",
                    leadingIcon = R.mipmap.shoujihao,
                    maxLength = 15,
                    onValueChange = {
                        phoneNumberStr = it
                        Log.e("Register", "onValueChange:$it")
                    })
            }

            //短信验证码
            Row(modifier = Modifier
                .constrainAs(smsCode) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(phoneNumber.bottom)
                }
                .padding(top = 30.dp, end = 35.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Box(modifier = Modifier.width(280.dp)) {
                    textFieldLayout(
                        false,
                        hint = "code",
                        leadingIcon = R.mipmap.duanxinyanzhengma,
                        maxLength = 15,
                        onValueChange = {
                            phoneSMsCodeStr = it
                            Log.e("Register", "onValueChange:$it")
                        },
                    )
                }

                Box(
                    modifier = Modifier
                        .height(35.dp)
                        .width(90.dp)
                ) {
                    CustomButton(
                        text = "send",
                        onClick = {
                            Log.e("Register", "Login")
                        },
                        contentColor = colorResource(id = R.color.white),
                        backgroundColor = colorResource(id = R.color.mega_brand1),
                    )
                }
            }


            //密码
            Box(modifier = Modifier
                .constrainAs(passCode) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(smsCode.bottom)
                }
                .padding(top = 30.dp)) {
                textFieldLayout(
                    true,
                    hint = "passcode",
                    leadingIcon = R.mipmap.mima,
                    maxLength = 20,
                    onValueChange = {
                        passCodeStr = it
                        Log.e("Register", "onValueChange:$it")
                    })
            }

            Box(modifier = Modifier
                .constrainAs(loginBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(sex.bottom)
                }
                .padding(start = 60.dp, end = 60.dp, top = 60.dp)) {
                CustomButton(
                    text = "register", onClick = {
                        Log.e("Register", "Login")
                    }, modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth()
                )
            }

            Box(modifier = Modifier
                .constrainAs(sex) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(passCode.bottom)
                }
                .padding(top = 15.dp)) {
                SingleChoiceComponent(options, selectedOption = selectoptions, onOptionSelected = {
                    selectoptions = it
                })
            }


        }
    }
}