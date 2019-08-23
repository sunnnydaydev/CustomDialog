package com.sunnyday.customdialog.views

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.service.dreams.DreamService
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.util.Log


import com.sunnyday.customdialog.R
import kotlinx.android.synthetic.main.dialog_simple.*

/**
 * Created by sunnyDay on 2019/8/22 19:33
 */
class SubscriptionDialog : Dialog {

    private var mOnContinueClickListener: OnContinueClickListener? = null

    /**
     *  use default dialog style
     * */
    constructor(context: Context) : super(context, R.style.SubscriptionDialog) {
        setContentView(R.layout.dialog_simple)
    }

    /**
     *  user give a custom dialog style
     * */
    constructor(context: Context, dialogStyle: Int) : super(context, dialogStyle) {
        setContentView(R.layout.dialog_simple)
    }

    /**
     * set image of dialog
     * */
    fun setImage(img: Int): SubscriptionDialog {
//        when (img) {
//            img is Int -> iv_img.setImageResource(img as Int)
//            img is Drawable -> iv_img.setImageDrawable(img as Drawable)
//            img is Bitmap -> iv_img.setImageBitmap(img as Bitmap)
//        }
        iv_img.setImageResource(img)
        return this
    }

    /**
     * set the content of dialog
     * */
    fun setContentText(content: String): SubscriptionDialog {
        tv_content.text = content
        return this
    }

    /**
     * set the content of dialog
     * */
    fun setContentTextColor(contextTextColor: Int): SubscriptionDialog {
        tv_content.setTextColor(contextTextColor)
        return this
    }

    /**
     * set text of button
     * */
    fun setButtonText(btnText: CharSequence): SubscriptionDialog {
        bt_button.text = btnText
        return this
    }

    /**
     * set color of button text
     * */
    fun setButtonTextColor(textColor: Int): SubscriptionDialog {
        bt_button.setTextColor(textColor)
        return this
    }

    /**
     * set color of button bg
     * */
    fun setButtonBackgroundColor(btnBgColor: Int): SubscriptionDialog {
        bt_button.setBackgroundColor(btnBgColor)
        return this
    }

    /**
     * onClickListener of dialog
     * */
    fun setOnContinueClickListener(onContinueClickListener: OnContinueClickListener?): SubscriptionDialog {
        mOnContinueClickListener = onContinueClickListener
        onButtonClick()
        return this
    }

    /**
     * show dialog
     * */
    fun showDialog() {
        this.show()
    }

    /**
     * close dialog
     * */
    fun dismissDialog() {
        this.dismiss()
    }

    private fun onButtonClick() {
        bt_button.setOnClickListener {
            mOnContinueClickListener?.onClick()
                ?: throw Exception("onContinueClickListener is null，please give a non-value.")
        }
    }

    interface OnContinueClickListener {
        fun onClick()
    }
}