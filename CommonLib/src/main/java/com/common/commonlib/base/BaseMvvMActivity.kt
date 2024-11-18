package com.common.commonlib.base

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 *  @Description:MVVM模式的Activity基类
 *  @author Jialin Huang
 *  @CreateDate 2024/6/14 16:48
 */
abstract class BaseMvvMActivity<VM : BaseViewModel> : BaseActivity() {
    var mViewModel: VM? = null
    private val showToastObserver =
        Observer<BaseModelObserver> { t ->
            showToast(t)
        }

    private val hideLoadingObserver =
        Observer<BaseModelObserver> { t ->
            t?.let {
                hideProgressDialog(t)
            }
        }

    private val showLoadingObserver =
        Observer<BaseModelObserver> { t ->
            t?.let {
                showProgressDialog(t)
            }
        }

    override fun initData() {
        mViewModel = createViewModel()
        mViewModel?.let {
            it.showToastMutableLiveData.observe(this, showToastObserver)
            it.hideLoadingMutableLiveData.observe(this, hideLoadingObserver)
            it.showLoadingMutableLiveData.observe(this, showLoadingObserver)
        }
        super.initData()
    }

    private fun showToast(data: BaseModelObserver) {
        data.content.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProgressDialog(data: BaseModelObserver) {
        showProgressDialog()
    }

    private fun hideProgressDialog(data: BaseModelObserver) {
        hideProgressDialog()
    }

    /**
     * 注意：这里不能使用 VM 来替代 T ，getViewModel是一个公共方法，会存在一个activity使用多个ViewModel情况
     * @return
     */
    fun <T : BaseViewModel> getViewModel(classModel: Class<T>): T {
        return ViewModelProvider(this)[classModel]
    }

    /**
     * ViewBinding 封装 不与父类关联，避免耦合过大
     * 使用：private val binding: ActivityMainBinding by inflate()
     */
    inline fun <reified VB : ViewBinding> Activity.inflate() = lazy {
        inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
    }

    inline fun <reified VB : ViewBinding> Dialog.inflate() = lazy {
        inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
        VB::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB


    override fun doSetContentView() {
        if (getLayout() > 0) {
            super.doSetContentView()
        }
    }

    override fun getLayout(): Int {
        return 0
    }

    protected abstract fun createViewModel(): VM

}