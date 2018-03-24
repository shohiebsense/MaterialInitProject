package com.shohiebsense.constraintlayoutexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shohiebsense.constraintlayoutexample.fragment.ContactListFragment
import com.shohiebsense.constraintlayoutexample.util.ActivityUtil.addFragment

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        addFragment(ContactListFragment(), R.id.layout_fragment)
    }
}
