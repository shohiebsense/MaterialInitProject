package com.shohiebsense.fintechtemplate.util

import android.app.AlarmManager
import android.app.LauncherActivity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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


    fun restartApplication(context: Context) {
        val mStartActivity = Intent(context, LauncherActivity::class.java)
        val mPendingIntentId = 123456
        val mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        System.exit(0)
    }

}
