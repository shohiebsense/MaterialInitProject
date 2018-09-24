package com.shohiebsense.fintechtemplate.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.shohiebsense.fintechtemplate.view.TabLayoutFragment

/**
 * Created by Shohiebsense on 23/01/2018.
 */
class TransferPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position ==0){
            return TabLayoutFragment.newInstance("0")
        }
        return TabLayoutFragment.newInstance("1")
    }

    override fun getCount(): Int {
        return 2
    }



}