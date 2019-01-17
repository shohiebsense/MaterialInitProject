package com.shohiebsense.fintechtemplate.util

import android.app.AlarmManager
import android.app.LauncherActivity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.os.Build
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Base64
import android.webkit.WebView
import com.shohiebsense.fintechtemplate.R
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

object AppUtil {

    fun convertDate(date: Date): String {
        val simpleDateFormat = SimpleDateFormat(
                "yyyyMMdd", Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    fun getToday(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }


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


    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    fun isExpiredFile(timeDownload: Long, maxDay: Int): Boolean {
        val maxtime = TimeUnit.DAYS.toMillis(maxDay.toLong())
        val timeNow = System.currentTimeMillis()
        return timeNow - timeDownload >= maxtime
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

    private val expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
    fun getVideoId(videoUrl: String?): String? {
        if (videoUrl == null || videoUrl.trim { it <= ' ' }.length <= 0) {
            return null
        }
        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(videoUrl)
        try {
            if (matcher.find())
                return matcher.group()
        } catch (ex: ArrayIndexOutOfBoundsException) {
            ex.printStackTrace()
        }

        return ""
    }


    fun getYoutubeThumbnailUrlFromVideoUrlDefault(videoUrl: String): String {
        val imgUrl = "http://img.youtube.com/vi/" + getVideoId(videoUrl) + "/0.jpg"
        return imgUrl
    }


    fun getIdTweet(linkTweet: String): Long {
        val temp_result = linkTweet.substring(linkTweet.lastIndexOf('/') + 1)
        var result = ""
        if (temp_result.indexOf('?') == -1) {
            result = temp_result
        } else {
            val index = temp_result.indexOf('?')
            result = temp_result.substring(0, index)
        }
        try {
            return java.lang.Long.parseLong(result)
        } catch (e: Exception) {
            return -1
        }

    }

    fun getDeviceOs(): String {
        return "android"
    }

    fun isTablet(context: Context): Boolean {
        return context.resources.getBoolean(R.bool.isTablet)
    }

    val DEVICE_TYPE_TABLET = "tablet"
    val DEVICE_TYPE_PHONE = "phone"
    fun getDeviceType(context: Context): String {
        return if (isTablet(context)) {
            DEVICE_TYPE_TABLET
        } else DEVICE_TYPE_PHONE
    }

    fun isDeviceTablet(context: Context): Boolean {
        val xlarge = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE
        val large = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }

    fun getDeviceName(): String {
        return Build.BRAND + Build.MODEL
    }


    fun setDeviceId(userId: Long?, email: String): String {
        var codeBytes = ByteArray(0)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            codeBytes = (email + userId + Build.BRAND + Build.MODEL).toByteArray(StandardCharsets.UTF_8)
        } else {
            try {
                codeBytes = (email + userId + Build.BRAND + Build.MODEL).toByteArray(charset("UTF-8"))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

        }
        val base64 = Base64.encodeToString(codeBytes, Base64.DEFAULT)
        return base64.replace("=", "")
    }

    fun addFacebookLayout(context: Context, postLink: String): WebView {
        val facebookWebView = WebView(context)
        val wholeFacebookLink = "https://www.facebook.com/plugins/post.php?href=%1\$s&_rdr&show_text=false"
        val frameLink = String.format(wholeFacebookLink, postLink)
        facebookWebView.settings.javaScriptEnabled = true
        facebookWebView.loadUrl(frameLink)
        return facebookWebView
    }

    fun setPartColorText(context: Context, harga: String): Spannable {
        val perBulanLabel = context.getString(R.string.per_month)
        val hargaSpannable = SpannableString(String.format(perBulanLabel, harga))
        hargaSpannable.setSpan(ForegroundColorSpan(context.resources.getColor(R.color.red)), perBulanLabel.indexOf(' ') + 1, perBulanLabel.indexOf(' ') + 1 + harga.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        hargaSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, hargaSpannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return hargaSpannable
    }

    fun setPartColorText(context: Context, harga: String, periode: Int): String {
        val perBulanLabel = context.getString(R.string.per_months)
        val redHarga = String.format(harga, context.resources.getColor(R.color.red))
        val hargaSpannable = SpannableString(String.format(perBulanLabel, harga, periode.toString()))

        hargaSpannable.setSpan(ForegroundColorSpan(context.resources.getColor(R.color.red)), perBulanLabel.indexOf(' ') + 1, harga.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        hargaSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, hargaSpannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        return String.format(perBulanLabel, redHarga, periode.toString())
    }

    fun isGifUrl(url: String): Boolean {
        val IMAGE_PATTERN = "([^\\s]+(\\.(?i)(|gif))$)"
        val pattern = Pattern.compile(IMAGE_PATTERN)
        val matcher = pattern.matcher(url)
        return matcher.matches()
    }
}
