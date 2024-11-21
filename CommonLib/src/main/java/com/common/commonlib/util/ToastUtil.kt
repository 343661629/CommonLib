package com.common.commonlib.util

import android.content.Context
import android.widget.Toast

/**
 * Author: Jialin Huang
 * Description:Toast工具类
 * Date: 2024/11/20
 */
object ToastUtil {
    private var toast: Toast? = null

    /**
     * 显示短时间的Toast
     * @param context 上下文
     * @param message 显示的信息
     */
    fun showShort(context: Context, message: CharSequence) {
        showToast(context, message, Toast.LENGTH_SHORT)
    }

    /**
     * 显示长时间的Toast
     * @param context 上下文
     * @param message 显示的信息
     */
    fun showLong(context: Context, message: CharSequence) {
        showToast(context, message, Toast.LENGTH_LONG)
    }

    /**
     * 显示Toast
     * @param context 上下文
     * @param message 显示的信息
     * @param duration 显示时长
     */
    private fun showToast(context: Context, message: CharSequence, duration: Int) {
        toast?.cancel() // 取消当前显示的Toast
        toast = Toast.makeText(context, message, duration)
        toast?.show()
    }
}