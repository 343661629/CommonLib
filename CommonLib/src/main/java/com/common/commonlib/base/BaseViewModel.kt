package com.common.commonlib.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.common.commonlib.model.UiState
import androidx.compose.runtime.mutableStateMapOf

/**
 *  @Description:ViewMpodel基类
 *  @author Jialin Huang
 *  @CreateDate 2024/6/14 17:07
 */
open class BaseViewModel : ViewModel() {

  /**
   * 业务可以通过String tag 来区分不同的toast
   */
  val showToastMutableLiveData: MutableLiveData<BaseModelObserver> by lazy {
    MutableLiveData<BaseModelObserver>()
  }

  /**
   * 业务可以通过String tag 来区分不同的loading
   */
  val showLoadingMutableLiveData: MutableLiveData<BaseModelObserver> by lazy {
    MutableLiveData<BaseModelObserver>()
  }

  /**
   * 业务可以通过String tag 来区分不同的loading
   */
  val hideLoadingMutableLiveData: MutableLiveData<BaseModelObserver> by lazy {
    MutableLiveData<BaseModelObserver>()
  }

  /**
   * 业务可以通过String tag 来区分不同的loading
   */
  val hideDialogMutableLiveData: MutableLiveData<BaseModelObserver> by lazy {
    MutableLiveData<BaseModelObserver>()
  }

}