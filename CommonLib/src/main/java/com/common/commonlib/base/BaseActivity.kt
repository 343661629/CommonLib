package com.common.commonlib.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.common.commonlib.view.dialog.LoadingDialog

/**
 * Author: Jialin Huang
 * Description: Activity基类
 * Date: 2024/11/18
 */
abstract class BaseActivity : ComponentActivity() {
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doSetContentView()
        initView()
        initData()
    }


    override fun onResume() {
        super.onResume()
    }


    open fun initData() {
    }


    open fun initView() {
    }


    abstract fun getLayout(): Int


    open fun doSetContentView() {
        setContentView(getLayout())
    }

    fun showProgressDialog() {
        loadingDialog?.let {
            if (it.isShowing) {
                return
            }
        }
        loadingDialog = LoadingDialog.Builder(this)
            .setMessage("loading...")
            .setCancelable(true)
            .setCancelOutside(false) // 点击弹框外是否可关闭
            .create()
        loadingDialog?.show()
    }


    fun hideProgressDialog() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }


}