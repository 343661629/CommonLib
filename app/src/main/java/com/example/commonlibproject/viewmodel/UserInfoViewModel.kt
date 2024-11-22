package com.example.commonlibproject.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.common.commonlib.base.BaseViewModel
import com.example.commonlibproject.model.SMSModel
import com.example.commonlibproject.network.NetWorkQuest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
    private val getSmMCode = MutableStateFlow<SMSModel?>(null) //主要是在viewmodel操作数据
    val getSmSResult: StateFlow<SMSModel?> = getSmMCode //对外




    /**
     * 发送短信验证码
     */
    fun sendSmsCode(phoneNumber:String){
        val type = 0 //0 获取注册验证码 1 修改密码验证码 2 验证码登录发送验证码
        netWorkQuest.getSMSCode<SMSModel>(type,phoneNumber){result: Result<SMSModel> ->
            result.onSuccess { data ->
                // 更新UI
                getSmMCode.value = data
                Log.e("NewVideModel", "data--->${data.msg},${data.code},${data.data}")
            }.onFailure { error ->
                // 处理错误
                val smsModel = SMSModel(-1,"","error")
                getSmMCode.value = smsModel
                Log.e("NewVideModel", "error--->${error.message}")
            }
        }
    }


    /**
     * 注册
     */
    fun register(phone: String,
                 code: String,
                 nickname: String,
                 password: String,
                 sex: Int,){
        netWorkQuest.register<SMSModel>(phone,code,nickname,password,sex){result: Result<SMSModel> ->
            result.onSuccess { data ->
                // 更新UI
                getSmMCode.value = data
                Log.e("NewVideModel", "data--->${data.msg},${data.code},${data.data}")
            }.onFailure { error ->
                // 处理错误
                val smsModel = SMSModel(-1,"","error")
                getSmMCode.value = smsModel
                Log.e("NewVideModel", "error--->${error.message}")
            }
        }
    }



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