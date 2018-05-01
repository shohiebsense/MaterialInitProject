package com.shohiebsense.constraintlayoutexample.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import java.util.*


/**
 * Created by Shohiebsense on 01/05/2018
 */

object LocaleManager {
    val LANGUAGE_ENGLISH = "en"
    val LANGUAGE_INDONESIAN = "id"
    private val LANGUAGE_KEY = "LANGUAGE_KEY"

    fun setLocale(c: Context): Context {
        return updateResources(c, getLanguage(c))
    }

    fun setNewLocale(c: Context, language: String): Context {
        persistLanguage(c, language)
        Log.e("shohiebsense ","new locale "+language)
        return updateResources(c, language)
    }

    fun getLanguage(c: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(c)
        return prefs.getString(LANGUAGE_KEY, LANGUAGE_INDONESIAN)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(c: Context, language: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(c)
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        Log.e("shohiebsensee ","new langugeage "+language)
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context, language: String): Context {
        var context = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        return context
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
    }
}
