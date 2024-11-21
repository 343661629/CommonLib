package com.example.commonlibproject.viewmodel

import com.common.commonlib.base.BaseModelObserver
import com.common.commonlib.base.BaseViewModel
import com.common.commonlib.util.launchOnMain
import com.example.commonlibproject.network.NetWorkQuest
import kotlinx.coroutines.CoroutineScope

/**
 * Author: Jialin Huang
 * Description:用户信息相关的ViewModel
 * Date: 2024/11/20
 */
class UserInfoViewModel:BaseViewModel() {
    companion object{
        const val TAG = "UserInfoViewModel"
    }
    private val netWorkQuest by lazy { NetWorkQuest() }



    /**
     * 登录
     */
    fun login(){
//        launchOnMain {
//            netWorkQuest.login().collect { result ->
//                result.onSuccess { data ->
//                    // 更新UI
//                    showToastMutableLiveData.postValue(BaseModelObserver(TAG, data.toString()))
//                }.onFailure { error ->
//                    // 处理错误
//                    showToastMutableLiveData.postValue(BaseModelObserver(TAG, error.message.toString()))
//                }
//            }
//        }

    }



}