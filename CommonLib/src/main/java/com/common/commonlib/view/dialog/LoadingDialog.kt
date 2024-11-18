package com.common.commonlib.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.common.commonlib.R

/**
 * Author: Jialin Huang
 * Description:
 * Date: 2024/11/18
 */
class LoadingDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {

    class Builder(private val context: Context) {
        private var message: String? = null
        private var isShowMessage: Boolean = true
        private var isCancelable: Boolean = false
        private var isCancelOutside: Boolean = false

        fun setMessage(message: String) = apply { this.message = message }
        fun setShowMessage(isShowMessage: Boolean) = apply { this.isShowMessage = isShowMessage }
        fun setCancelable(isCancelable: Boolean) = apply { this.isCancelable = isCancelable }
        fun setCancelOutside(isCancelOutside: Boolean) = apply { this.isCancelOutside = isCancelOutside }

        fun create(): LoadingDialog {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
            val loadingDialog = LoadingDialog(context, R.style.MyProgressDialog)
            val msgText:TextView = view.findViewById(R.id.messageTextView)
            if (isShowMessage) {
                msgText.text = message
            } else {
                msgText.visibility = View.GONE
            }
            loadingDialog.setContentView(view)
            loadingDialog.setCancelable(isCancelable)
            loadingDialog.setCanceledOnTouchOutside(isCancelOutside)
            return loadingDialog
        }
    }
}