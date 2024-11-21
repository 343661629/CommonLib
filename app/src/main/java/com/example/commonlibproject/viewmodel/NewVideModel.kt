package com.example.commonlibproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.common.commonlib.util.launchOnMain
import com.example.commonlibproject.model.NewTypeModel
import com.example.commonlibproject.network.NetWorkQuest

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
//        launchOnMain {
//            netWorkQuest.getNewsTypeList().collect { result ->
//                result.onSuccess { data ->
//                    // 更新UI
//                    Log.e("NewVideModel", "data--->$data")
//                }.onFailure { error ->
//                    // 处理错误
//                    Log.e("NewVideModel", "error--->${error.message}")
//                }
//            }
//        }

        netWorkQuest.getNewsTypeList<NewTypeModel> (1){ result ->
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


