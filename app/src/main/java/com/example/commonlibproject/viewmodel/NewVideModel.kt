package com.example.commonlibproject.viewmodel

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import com.example.commonlibproject.network.NetWorkQuest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
class NewVideModel : ViewModel() {
    private val netWorkQuest by lazy { NetWorkQuest() }


    /**
     * 获取新闻类型
     */
    fun getNewType() {
        GlobalScope.launch {
            netWorkQuest.getNewsTypeList().collect { result ->
                result.onSuccess { data ->
                    // 更新UI
                    Log.e("NewVideModel", "data--->$data")
                }.onFailure { error ->
                    // 处理错误
                    Log.e("NewVideModel", "error--->${error.message}")
                }
            }

        }
    }



}


