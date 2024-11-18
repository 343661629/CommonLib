package com.example.commonlibproject.ui.page

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.commonlibproject.R

/**
 * Author: Jialin Huang
 * Description:标题栏
 * Date: 2024/11/18
 */

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun test() {
    customTitleBar()
}

@Composable
fun customTitleBar(
    titleStr: String = "title",
    rightText: String = "",
    callBack: (() -> Unit)? = null,
    rightCallBack: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val activity = context as? Activity
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = colorResource(id = R.color.color_real_time_vide_heart_sleep))
    ) {
        val (backImage, title, right) = createRefs()
        Box(modifier = Modifier
            .constrainAs(backImage) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .clickable {
                callBack?.invoke() ?: run {
                    activity?.finish()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.fanhui), contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Text(text = titleStr,
            style = TextStyle(color = colorResource(id = R.color.white), fontSize = 18.sp),
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        Box(modifier = Modifier
            .padding(end = 10.dp)
            .constrainAs(right) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            .clickable {
                rightCallBack?.invoke()
            }) {
            Text(
                text = rightText,
                style = TextStyle(color = colorResource(id = R.color.teal_700), fontSize = 16.sp),
                modifier = Modifier

            )
        }


    }


}




