package com.common.commonlib.util

import android.os.CountDownTimer

/**
 * Author: Jialin Huang
 * Description:倒计时工具类
 * Date: 2024/11/21
 * 使用：countdownTimerUtil = CountdownTimerUtil(
 *             millisInFuture = 60000, // 60 seconds
 *             countDownInterval = 1000, // 1 second
 *             onTick = { millisUntilFinished ->
 *                 textView.text = "Time remaining: ${millisUntilFinished / 1000} seconds"
 *             },
 *             onFinish = {
 *                 textView.text = "Countdown finished!"
 *             }
 *         )
 *
 *         countdownTimerUtil.start()
 *
 *         最后记得取消
 */
class CountdownTimerUtil(
    private val millisInFuture: Long,
    private val countDownInterval: Long,
    private val onTick: (millisUntilFinished: Long) -> Unit,
    private val onFinish: () -> Unit
) {

    private var countDownTimer: CountDownTimer? = null
    fun start() {
        countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    fun cancel() {
        countDownTimer?.cancel()
    }


}