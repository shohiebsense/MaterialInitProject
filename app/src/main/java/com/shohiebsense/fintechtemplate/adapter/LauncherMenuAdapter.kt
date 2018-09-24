package com.shohiebsense.fintechtemplate.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shohiebsense.fintechtemplate.R
import com.shohiebsense.fintechtemplate.model.LauncherMenu
import com.shohiebsense.fintechtemplate.view.BasicViewHolder
import kotlinx.android.synthetic.main.item_launcher.view.*


/**
 * Created by Shohiebsense on 22/04/2018
 */

class LauncherMenuAdapter(val launcherMenus : MutableList<LauncherMenu>) : RecyclerView.Adapter<BasicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_launcher,parent,false)
        return BasicViewHolder(view)

    }

    override fun getItemCount(): Int = launcherMenus.size

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        val launcherMenu = launcherMenus[position]
        holder.itemView.button_launcher.setText(launcherMenu.name)
        holder.itemView.button_launcher.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context,launcherMenu.classDestination))
        }
    }


}