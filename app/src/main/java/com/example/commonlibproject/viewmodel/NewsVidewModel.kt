package com.example.commonlibproject.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.commonlibproject.model.Detail
import com.example.commonlibproject.model.NewDetailModel
import com.example.commonlibproject.model.NewListItemModel
import com.example.commonlibproject.model.NewTypeModel
import com.example.commonlibproject.model.NewsListModel
import com.example.commonlibproject.model.SMSModel
import com.example.commonlibproject.network.NetWorkQuest
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/17
 */
class NewsVidewModel : ViewModel() {
    private val netWorkQuest by lazy { NetWorkQuest() }

    private val newTypeModel = MutableStateFlow<NewTypeModel?>(null)
    val newsTypeResult = newTypeModel

    private val newListData = MutableStateFlow<List<NewListItemModel>>(emptyList())
    val newsListState = newListData

    private val newsDetailResult = MutableStateFlow<Detail?>(null)
    val newsDetailState = newsDetailResult




    /**
     * 获取新闻类型
     */
    fun getNewType() {
        netWorkQuest.getNewType<NewTypeModel>() { result ->
            result.onSuccess { it ->
                it.data[0].isSelectPosition = true
                newTypeModel.value = it
                // 更新UI
                Log.e("NewVideModel", "data--->${it.msg},${it.code},${it.data}")
            }.onFailure { error ->
                val model = NewTypeModel(-1, emptyList(), "error")
                newTypeModel.value = model
                // 处理错误
                Log.e("NewVideModel", "error--->${error.message}")
            }
        }
    }


    /**
     * 获取新闻列表
     */
    fun getNewsList(typeId: String, page: String) {
        netWorkQuest.getNewsList<NewsListModel>(typeId, page) { result ->
            result.onSuccess { data ->
                Log.e("NewVideModel", "data--->${data.msg},${data.code},${data.data}")
                data.takeIf { it.code == 1 }?.let {
                    newListData.value = it.data
                }?:run {
                    // 处理错误
                    Log.e("NewVideModel", "error--1->${data.msg}")
                }

            }.onFailure { error ->
                // 处理错误
                Log.e("NewVideModel", "error--->${error.message}")
            }
        }
    }


    /**
     * 获取新闻详情
     */
    fun getNewsDetail(newsId:String) {
        netWorkQuest.getNewDetail<NewDetailModel>(newsId) { result ->
            result.onSuccess { it ->
                newsDetailResult.value = it.data
                // 更新UI
                Log.e("NewVideModel", "data--->${it.msg},${it.code},${it.data}")
            }.onFailure { error ->
                // 处理错误
                Log.e("NewVideModel", "error--->${error.message}")
            }
        }
    }


}


