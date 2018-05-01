package com.shohiebsense.constraintlayoutexample.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shohiebsense.constraintlayoutexample.R
import com.shohiebsense.constraintlayoutexample.model.ChosenLanguage
import com.shohiebsense.constraintlayoutexample.util.AppUtil
import com.shohiebsense.constraintlayoutexample.util.LocaleManager
import kotlinx.android.synthetic.main.item_language.view.*


/**
 * Created by Shohiebsense on 01/05/2018
 */

class LanguageAdapter(val languages : List<ChosenLanguage>) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_language, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = languages.size

    override fun onBindViewHolder(holder: LanguageAdapter.ViewHolder, position: Int) {
        holder.fetch(languages[position])
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        fun fetch(language : ChosenLanguage){
            itemView.text_language_name.text = language.languageName
            val currentLanguage = LocaleManager.getLanguage(itemView.context)
            Log.e("shohiebsenseee ",currentLanguage)
            itemView.text_language_name.setCompoundDrawablesWithIntrinsicBounds(android.R.color.transparent,android.R.color.transparent,if(currentLanguage.equals(language.languageCode,true)) R.drawable.ic_check_black_24dp else android.R.color.transparent ,android.R.color.transparent)

            itemView.setOnClickListener {
                Log.e("shohiebsenseee ","clickeedd "+language)
                if(language.languageName.equals(itemView.context.getString(R.string.indonesian))){
                    LocaleManager.setNewLocale(itemView.context, LocaleManager.LANGUAGE_INDONESIAN)
                }
                else if(language.languageName.equals(itemView.context.getString(R.string.english))){
                    LocaleManager.setNewLocale(itemView.context, LocaleManager.LANGUAGE_ENGLISH)
                }
                AppUtil.restartApplication(itemView.context)
            }
        }



    }
}