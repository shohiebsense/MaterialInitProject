package com.shohiebsense.constraintlayoutexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.shohiebsense.constraintlayoutexample.adapter.LanguageAdapter
import com.shohiebsense.constraintlayoutexample.model.ChosenLanguage
import com.shohiebsense.constraintlayoutexample.util.LocaleManager
import kotlinx.android.synthetic.main.activity_language_setting.*

class LanguageSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_setting)
        title = getString(R.string.setting_language)
        recycler_language.layoutManager = LinearLayoutManager(this)
        recycler_language.adapter = LanguageAdapter(fetchLanguages())

    }
    fun fetchLanguages() : List<ChosenLanguage>{
        val languages = arrayListOf<ChosenLanguage>()
        languages.add(ChosenLanguage(getString(R.string.english), LocaleManager.LANGUAGE_ENGLISH))
        languages.add(ChosenLanguage(getString(R.string.indonesian),LocaleManager.LANGUAGE_INDONESIAN))
        return languages
    }

}
