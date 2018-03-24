package com.shohiebsense.constraintlayoutexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import com.shohiebsense.constraintlayoutexample.view.ExpandableAdapter
import kotlinx.android.synthetic.main.activity_expandable_item.*

class ExpandableItemActivity : AppCompatActivity(), ExpandableAdapter.ItemClickListener {


    lateinit var adapter: ExpandableAdapter
    private val SAVED_STATE_EXPANDABLE_ITEM_MANAGER = "RecyclerViewExpandableItemManager"
    lateinit var wrappedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
    lateinit var mRecyclerViewExpandableItemManager : RecyclerViewExpandableItemManager
    var isInProgress = false
    val onTouchListener = View.OnTouchListener { v, event ->
        dispatchTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_item)
        val eimSavedState = savedInstanceState?.getParcelable<Parcelable>(SAVED_STATE_EXPANDABLE_ITEM_MANAGER)
        mRecyclerViewExpandableItemManager = RecyclerViewExpandableItemManager(eimSavedState)
        mRecyclerViewExpandableItemManager.defaultGroupsExpandedState = false
        val animator = RefactoredDefaultItemAnimator()

        adapter = ExpandableAdapter(mRecyclerViewExpandableItemManager,this)
        wrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(adapter)
        recycler.itemAnimator = animator
        recycler.setHasFixedSize(false)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = wrappedAdapter
        mRecyclerViewExpandableItemManager.attachRecyclerView(recycler)
        layout_root_register.setOnTouchListener(onTouchListener)
    }

    override fun onRegister() {
       // isInProgress = true

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
       /* if(isInProgress){
            return true
        }*/
        return super.dispatchTouchEvent(ev);
    }

}
