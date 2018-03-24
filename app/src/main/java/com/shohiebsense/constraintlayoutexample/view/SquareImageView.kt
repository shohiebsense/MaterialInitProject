package com.shohiebsense.constraintlayoutexample.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by Shohiebsense on 02/03/2018.
 */
class SquareImageView : ImageView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}