package com.shohiebsense.fintechtemplate

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.shohiebsense.fintechtemplate.util.LocaleManager


/**
 * Created by Shohiebsense on 01/05/2018
 */

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    override fun onCreate() {
        super.onCreate()
        LocaleManager.setLocale(this)
    }
}