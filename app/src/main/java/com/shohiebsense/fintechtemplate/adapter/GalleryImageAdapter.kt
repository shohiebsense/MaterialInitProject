package com.shohiebsense.fintechtemplate.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shohiebsense.fintechtemplate.GalleryActivity
import com.shohiebsense.fintechtemplate.R
import com.shohiebsense.fintechtemplate.model.ProfileImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import java.io.File

/**
 * Created by Shohiebsense on 06/02/2018.
 */
class GalleryImageAdapter(val productImages: ArrayList<ProfileImage>, val listener: OnImageClickedListener,
                          val activity: Activity) :
        RecyclerView.Adapter<GalleryImageAdapter.ViewHolder>() {


    var isHomeGalleryDir = true

    fun updateItems(productImages: ArrayList<ProfileImage>, bucketImageId: Boolean){
        this.isHomeGalleryDir = bucketImageId
        val diffResult = DiffUtil.calculateDiff(ImageDiffUtil(this.productImages, productImages))
        this.productImages.clear()
        this.productImages.addAll(productImages)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder!!.fetch(productImages[position])

    }


    override fun getItemCount(): Int = productImages.size



    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        fun fetch(productImage: ProfileImage){
            Picasso.with(mView.context).load(File(productImage.path)).noFade().resize(250,250).into(itemView.image_gallery)
            itemView.text_imagename.text = if(isHomeGalleryDir) productImage.bucketName else productImage.name
            itemView.setOnClickListener {
                if(isHomeGalleryDir){
                    val intent = Intent(activity, GalleryActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or intent.flags)
                    intent.putExtra(GalleryActivity.INTENT_BUCKET_ID, productImage.id)
                    intent.putExtra(GalleryActivity.INTENT_IS_HOME_DIR,false)
                    activity.startActivityForResult(intent, 2)
                }
                else{
                    listener.onImageClicked(productImage)
                }
            }
        }
    }


    inner class ImageDiffUtil(val oldList : ArrayList<ProfileImage>, val newList : ArrayList<ProfileImage>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList.get(oldItemPosition).id == newList.get(newItemPosition).id

    }

    interface OnImageClickedListener {
        fun onImageClicked(productImage: ProfileImage)
    }

}