package com.shohiebsense.fintechtemplate

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar


/**
 * Created by Shohiebsense on 22/04/2018
 */

open class BaseActivity: AppCompatActivity() {



    fun initToolbar(toolbar : Toolbar){
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }



}