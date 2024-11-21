package com.common.commonlib.base

import android.os.Bundle
import androidx.activity.ComponentActivity

/**
 * Author: Jialin Huang
 * Description:compose activity基类
 * Date: 2024/11/20
 */
open class BaseComposeActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}