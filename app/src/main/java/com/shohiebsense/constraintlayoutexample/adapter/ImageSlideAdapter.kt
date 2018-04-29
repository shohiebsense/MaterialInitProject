package com.shohiebsense.constraintlayoutexample.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shohiebsense.constraintlayoutexample.R
import com.shohiebsense.constraintlayoutexample.model.CommonImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_slide_image.view.*


/**
 * Created by Shohiebsense on 28/04/2018
 */

class ImageSlideAdapter(val commonImageList : MutableList<CommonImage>):   RecyclerView.Adapter<ImageSlideAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_slide_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = commonImageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(holder.itemView.context)
                .load(commonImageList[position].url)
                .into(holder.itemView.image)
        holder.itemView.image.setOnClickListener {

        }
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

    }
}
