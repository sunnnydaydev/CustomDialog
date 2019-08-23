package com.sunnyday.customdialog

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sunnyday.customdialog.views.SubscriptionDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text.text = "自定义对话框"

    }

    fun doClick(view: View) {

        val dialog = SubscriptionDialog(this)
        dialog
            .setImage(R.drawable.peiqi)
            .setContentText("hello custom dialog 、hello custom dialog 、hello custom dialog ")
            .setContentTextColor(Color.RED)
            .setButtonText("sunny")
            .setButtonTextColor(Color.BLACK)
            .setButtonBackgroundColor(Color.parseColor("#FFD81B60"))
            .setOnContinueClickListener(object : SubscriptionDialog.OnContinueClickListener {
                override fun onClick() {
                    Toast.makeText(applicationContext, "simple dialog", Toast.LENGTH_LONG).show()
                    dialog.dismissDialog()
                }
            })
            .showDialog()
    }
}
