package com.shohiebsense.constraintlayoutexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.leochuan.CenterSnapHelper
import com.leochuan.ScaleLayoutManager
import com.shohiebsense.constraintlayoutexample.adapter.ImageSlideAdapter
import com.shohiebsense.constraintlayoutexample.model.CommonImage
import com.shohiebsense.constraintlayoutexample.network.getimages.GetImagesFromWebTask
import com.shohiebsense.constraintlayoutexample.util.AppUtil
import kotlinx.android.synthetic.main.activity_half_colored_background.*

class HalfColoredBackgroundActivity : AppCompatActivity(), GetImagesFromWebTask.GetImagesListener {


    lateinit var layoutManager : ScaleLayoutManager
    lateinit var imageList : MutableList<CommonImage>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_half_colored_background)

        layoutManager = ScaleLayoutManager.Builder(this, AppUtil.Dp2px(this, 5f))
                .setMinScale(0.95f)
                .build()
        CenterSnapHelper().attachToRecyclerView(recycler_example)
        GetImagesFromWebTask().getImages(this)
    }

    override fun onSuccess(commonImages: MutableList<CommonImage>) {
        imageList = commonImages
        recycler_example.layoutManager = layoutManager
        recycler_example.adapter = ImageSlideAdapter(imageList)
    }

    override fun onFailed() {

    }


}
