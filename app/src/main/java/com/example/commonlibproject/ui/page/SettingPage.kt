package com.example.commonlibproject.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commonlibproject.R

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/19
 */

@Composable
fun SettingPage() {
    Column {
        Box {
            customTitleBar(isNeedBack = false, titleStr = "Setting", titleColor = R.color.white)
        }
        SettingHeader()
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingHeader() {
    val userName by remember {
        mutableStateOf("Jialin Huang")
    }
    val phone by remember {
        mutableStateOf("13745678954")
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.mega_container_secondary_60_percent))
                .padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.mipmap.yuanxingtouxiang),
                    contentDescription = null
                )
                Column {
                    Text(
                        text = userName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.black)
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = phone,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.mega_wheel_text_center)
                        ),
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 35.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.jifenshangcheng,
                leftTitle = "shopping",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }


        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.youhuijuan,
                leftTitle = "coupon",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }

        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.shoucang,
                leftTitle = "favorite",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }


        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.xiaoxizhongxin,
                leftTitle = "messgae",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }

        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.fanqizha1,
                leftTitle = "security",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }


        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 30.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.guanyuwomen,
                leftTitle = "about us",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }
        Box(modifier = Modifier.padding(start = 15.dp, end = 15.dp).background(
            colorResource(id = R.color.white), shape = RoundedCornerShape(5.dp)
        )) {
            itemSetting(leftImage = R.mipmap.qingchuhuancun,
                leftTitle = "clear",
                rightTitle = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).padding(start = 10.dp),
                onClick = { })
        }


    }

}


