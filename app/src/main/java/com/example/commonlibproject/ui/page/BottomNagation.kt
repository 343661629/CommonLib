package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commonlibproject.R
import com.example.commonlibproject.model.BottomItemModel

/**
 * Author: Jialin Huang
 * Description:底部导航栏，后面需要换成LazyColumn实现
 * Date: 2024/11/18
 */

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun textUi() {
//    val items = listOf(
//        BottomItemModel("Home", R.mipmap.shouyeweixuanzhong, false),
//        BottomItemModel("News", R.mipmap.xinwenweixuanzhong, false),
//        BottomItemModel("Story", R.mipmap.gushixuanzhong, false),
//        BottomItemModel("lottery", R.mipmap.caipiaoxuanzhong, true),
//    )
//    bottomNavigation(items = items, onClickItem = {})
}


@Composable
fun bottomNavigation(
    items:MutableList<BottomItemModel>,
    onClickItem: (index: Int) -> Unit
) {
    Row(
        modifier = Modifier
    ) {
        items.forEachIndexed() { index, item ->
            Log.d("bottomNavigation", "bottomNavigation: ${item.isSelect}")
            Box(modifier = Modifier
                .weight(1f)
                .clickable {
                    onClickItem(index)
                }, contentAlignment = Alignment.Center) {
                bottomItem(item.name, item.icon, item.isSelect.value)
            }

        }
    }
}


@Composable
fun bottomItem(name: String, icon: Int, isSelect: Boolean) {
    Column {
        Spacer(modifier = Modifier.height(5.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name,
            style = TextStyle(
                fontSize = 12.sp,
                color = colorResource(id = if (isSelect) R.color.teal_200 else R.color.black)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}

