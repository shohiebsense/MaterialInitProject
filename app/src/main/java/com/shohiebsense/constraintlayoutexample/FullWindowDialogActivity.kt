package com.shohiebsense.constraintlayoutexample

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_input_dialog_example.*
import me.philio.pinentry.PinEntryView

class FullWindowDialogActivity : AppCompatActivity() {

    lateinit var dialog : Dialog
    lateinit var smsVerificationCode : PinEntryView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_window_dialog)
        initExampleDialog()
    }

    fun initExampleDialog(){
        dialog = Dialog(this,android.R.style.Theme_DeviceDefault_Light_NoActionBar)
        dialog!!.setContentView(R.layout.fragment_input_dialog_example);
        dialog!!.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        smsVerificationCode = dialog.edit_text_sms_verification
        smsVerificationCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().length == 6){
                    Log.e("shohiebsense ","hello....")
                    dialog.dismiss()
                    finish()
                }
            }

        })
        dialog.button_cancel.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

}
