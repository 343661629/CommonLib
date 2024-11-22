package com.example.commonlibproject.ui.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commonlibproject.R
import com.example.commonlibproject.ui.theme.color_45a7ff

/**
 * Author: Jialin Huang
 * Description:一些组件封装在该文件中
 * Date: 2024/11/20
 */

@Composable
fun textFieldLayout(
    isPassCode: Boolean = false,
    hint: String = "",
    maxLength: Int = 20,
    leadingIcon: Int = R.mipmap.yonghuming,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange: (String) -> Unit
) {
    var inputStr by remember { mutableStateOf("") }
    Box(modifier = Modifier.padding(start = 50.dp, end = 50.dp)) {
        TextField(
            value = inputStr,
            onValueChange = {
                if (it.length <= maxLength) {
                    inputStr = it
                    onValueChange(it)
                }
            },
            modifier = modifier,
            textStyle = TextStyle(
                fontSize = 16.sp, color = Color.Black
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassCode) KeyboardType.Password else KeyboardType.Text
            ),
            visualTransformation = if (isPassCode) PasswordVisualTransformation() else VisualTransformation.None,//是否明文显示
            placeholder = {
                Text(text = hint, color = Color.Gray)
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.mipmap.qingchu),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            inputStr = ""
                        }
                        .size(20.dp)
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            inputStr = ""
                        }
                        .size(25.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                //disabledContainerColor = Color.Green,
                unfocusedContainerColor = Color.Transparent,
            ),

            )
    }
}


/**
 * button
 */
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    backgroundColor: Color = color_45a7ff,
    contentColor: Color = Color.White,
    textSize: TextUnit = 16.sp,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Log.e("CustomButton", "text:$text")
        Text(
            text = text,
            fontSize = textSize,
        )
    }
}


/**
 * 单选框
 */
@Composable
fun SingleChoiceComponent(
    options: List<String> = mutableListOf(),
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.Center) {
        options.takeIf { it.isNotEmpty() }?.let {
            options.forEach { option ->
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    RadioButton(
                        selected = option == selectedOption,
                        onClick = { onOptionSelected(option) }
                    )
                    Text(
                        text = option,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(CenterVertically),
                    )
                }
            }
        }
    }
}

