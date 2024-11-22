package com.example.commonlibproject.ui.page

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.common.commonlib.util.ToastUtil
import com.common.commonlib.util.launchOnMain
import com.example.commonlibproject.R
import com.example.commonlibproject.common.pageBackground
import com.example.commonlibproject.viewmodel.UserInfoViewModel
import com.network.networklibrary.util.GsonHelper
import kotlinx.coroutines.delay

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */

@Composable
fun registerPage(navController: NavHostController, counterViewModel: UserInfoViewModel) {
    var userNameStr by remember { mutableStateOf("") }
    var phoneNumberStr by remember { mutableStateOf("") }
    var phoneSMsCodeStr by remember { mutableStateOf("") }
    var passCodeStr by remember { mutableStateOf("") }
    val options = mutableListOf("male", "female")
    var selectoptions by remember {
        mutableStateOf(options[0])
    }
    val context = LocalContext.current
    var isabled by remember {
        mutableStateOf(true)
    }


    val smsCodeResult = counterViewModel.getSmSResult.collectAsState().value
    Log.e("Register", "smsCodeResult--->${GsonHelper.toJson(smsCodeResult)}")
    smsCodeResult?.let {
        if (it.code == 1) {
            isabled = false
            ToastUtil.showShort(context, "data send success")
        } else {
            isabled = true
            ToastUtil.showShort(context, "data send fail")
        }
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
                        enabled = isabled,
                        onClick = {
                            if (TextUtils.isEmpty(phoneNumberStr)) {
                                Log.e("Register", "phone number is empty")
                                ToastUtil.showShort(context, "phone number is empty")
                                return@CustomButton
                            }
                            //send code
                            counterViewModel.sendSmsCode(phoneNumberStr)
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
                        if (TextUtils.isEmpty(userNameStr)) {
                            ToastUtil.showShort(context, "user name is empty")
                            return@CustomButton
                        }
                        if (TextUtils.isEmpty(phoneNumberStr)) {
                            ToastUtil.showShort(context, "phone number is empty")
                            return@CustomButton
                        }

                        if (TextUtils.isEmpty(phoneSMsCodeStr)) {
                            ToastUtil.showShort(context, "phone sms code is empty")
                            return@CustomButton
                        }
                        if (TextUtils.isEmpty(passCodeStr)) {
                            ToastUtil.showShort(context, "pass code is empty")
                            return@CustomButton
                        }
                        counterViewModel.register(phoneNumberStr, phoneSMsCodeStr, userNameStr, passCodeStr, if(selectoptions == options[0]) 0 else 1)
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