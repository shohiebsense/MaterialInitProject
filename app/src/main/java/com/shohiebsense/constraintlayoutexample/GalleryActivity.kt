package com.shohiebsense.constraintlayoutexample

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.shohiebsense.constraintlayoutexample.model.ProfileImage
import com.shohiebsense.constraintlayoutexample.util.ImageGalleryUtil
import com.shohiebsense.constraintlayoutexample.adapter.GalleryImageAdapter
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), ImageGalleryUtil.ImagesGalleryListener,  GalleryImageAdapter.OnImageClickedListener {


    lateinit var galleryImageAdapter: GalleryImageAdapter
    var bucketImageId = ""
    var isGalleryHomeDir = true
    val REQUEST_STORAGE_PERMISSION = 1


    companion object {
        const val INTENT_IS_HOME_DIR = "ishomedir"
        const val INTENT_BUCKET_ID = "bucketid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        if(intent != null){
            isGalleryHomeDir = intent.getBooleanExtra(INTENT_IS_HOME_DIR,false)
            if(!isGalleryHomeDir) {
                bucketImageId = intent.getStringExtra(INTENT_BUCKET_ID)
            }
        }


        requestStorageAndCameraPermission()


    }

    private fun getImageGallery(){
        ImageGalleryUtil(this, this).getImages(bucketImageId)
    }

    override fun onFetched(productImages: ArrayList<ProfileImage>) {
        galleryImageAdapter.updateItems(productImages, bucketImageId.isBlank())
    }

    override fun onImageClicked(productImage: ProfileImage) {
        val intent = Intent()
        Log.e("shohiebsense ","image clicked")
        //intent.putExtra(ProfileActivity.INTENT_IMAGE, productImage)
       // intent.putExtra(ProfileActivity.INTENT_FLAG_IMAGE_GATHERED, true)
        setResult(Activity.RESULT_OK, intent)
        System.out.println("IMAGE CLICKED")
        //finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    System.out.println("ALBUM CLICKED")
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
            }
        }
    }

    private fun requestStorageAndCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_STORAGE_PERMISSION)
        }
        else{
            init()
        }
    }

    fun init(){
        galleryImageAdapter = GalleryImageAdapter(arrayListOf(), this, this@GalleryActivity)
        val layoutManager = GridLayoutManager(this,2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recycler_image.layoutManager = layoutManager
        recycler_image.adapter = galleryImageAdapter
        getImageGallery()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if ((grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED)) {
                //Toasty.error(this,getString(R.string.request_notification_info)).show()
                finish()
                return
            }
            init()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}
