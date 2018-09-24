package com.shohiebsense.fintechtemplate.util

import android.app.Activity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import com.shohiebsense.fintechtemplate.model.ProfileImage

/**
 * Created by Shohiebsense on 01/03/2018.
 */
class ImageGalleryUtil(val activity: Activity, val listener : ImagesGalleryListener) : LoaderManager.LoaderCallbacks<Cursor> {

    var bucketId = ""
    val IMAGE_LOADER_ID = 0
    val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    val projections = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.DISPLAY_NAME)
    var sortOrder = MediaStore.Images.Media.DATA + " DESC"

    fun getImages(bucketImageId: String) {
        this.bucketId = bucketImageId
        activity.loaderManager.initLoader(IMAGE_LOADER_ID, null, this)
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>? {
        if (bucketId.isBlank()) {
            return CursorLoader(activity.applicationContext,
                    uri,
                    projections,
                    null, null,
                    sortOrder)
        } else {
            return CursorLoader(activity.applicationContext,
                    uri,
                    projections,
                    projections[3] + " = \"" + bucketId + "\"",
                    null,
                    sortOrder)
        }
        return null
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, cursor: Cursor?) {
        val photos = ArrayList<ProfileImage>()
        val ids = HashSet<String>()
        while (cursor!!.moveToNext()) {
            val imageName = cursor.getString(cursor.getColumnIndex(projections[4]))
            val imageBucket = cursor.getString(cursor.getColumnIndex(projections[2]))
            val bucketId = cursor.getString(cursor.getColumnIndex(projections[3]))
            val imagePath = cursor.getString(cursor.getColumnIndex(projections[1]))

            val imagePhoto = ProfileImage(bucketId, imageBucket, imageName, imagePath)

            if (this.bucketId.isBlank()) {
                if (ids.add(bucketId)) {
                    photos.add(imagePhoto)
                }
            } else {
                if (ids.add(imagePath)) {
                    photos.add(imagePhoto)
                }
            }

        }
        cursor.close()
        listener.onFetched(photos)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {

    }


    fun getImagesByCategory(position: Int) {
        activity.loaderManager.initLoader(IMAGE_LOADER_ID, null, this)
    }


    interface ImagesGalleryListener {
        fun onFetched(productImages: ArrayList<ProfileImage>)
    }
}