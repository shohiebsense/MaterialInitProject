package com.shohiebsense.constraintlayoutexample.util

import android.content.Context
import android.os.CountDownTimer
import java.text.SimpleDateFormat

object AppUtil {


    fun getDateFormFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
    }

    fun getDateCodeFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyMMdd")
    }

    fun convertIndonesianCode(phoneNumber: String): String {
        return if (phoneNumber.startsWith("+62")) phoneNumber.replaceFirst("\\+62".toRegex(), "0") else phoneNumber
    }

    fun Dp2px(context: Context?, dp: Float): Int {
        if (context != null) {
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }
        return 2
    }

    fun getCountDown(){
        object : CountDownTimer(60000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                var timeInSecond =  millisUntilFinished / 1000
            }

            override fun onFinish() {

            }
        }.start()
    }

}
