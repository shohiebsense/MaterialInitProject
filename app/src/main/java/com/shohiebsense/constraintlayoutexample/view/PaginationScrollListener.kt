package com.shohiebsense.constraintlayoutexample.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils.findFirstVisibleItemPosition



/**
 * Created by Shohiebsense on 21/03/2018.
 */
abstract class PaginationScrollListener(val linearLayoutManager : LinearLayoutManager) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = linearLayoutManager.getChildCount()
        val totalItemCount = linearLayoutManager.getItemCount()
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun getTotalPageCount(): Int

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}