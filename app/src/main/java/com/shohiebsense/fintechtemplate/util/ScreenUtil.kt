package com.shohiebsense.fintechtemplate.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager

object ScreenUtil {

    fun hideKeyboard(act: Activity) {
        try {
            val view = act.currentFocus
            val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    fun pixelsToDp(activity: Activity, px: Int): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val logicalDensity = metrics.density
        return Math.ceil((px / logicalDensity).toDouble()).toInt()
    }

    fun dpToPixel(activity: Activity, dp: Int): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val logicalDensity = metrics.density
        return Math.ceil((dp * logicalDensity).toDouble()).toInt()
    }

    fun getScreenWidth(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }



}