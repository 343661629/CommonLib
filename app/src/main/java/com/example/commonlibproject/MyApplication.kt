package com.example.commonlibproject

import android.app.Application
import com.common.commonlib.util.MMKVUtil

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/20
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MMKVUtil.initialize(this)
    }
}