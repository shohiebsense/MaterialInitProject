package com.shohiebsense.fintechtemplate

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.support.v4.view.ViewPager
import android.view.View
import com.shohiebsense.fintechtemplate.fragment.ContactListFragment
import com.shohiebsense.fintechtemplate.util.ActivityUtil.addFragment
import java.lang.ref.WeakReference

class ContactsActivity : AppCompatActivity() {

    val PERMISSION_REQUEST_CONTACT = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        checkContactPermission()
    }

    private fun checkContactPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST_CONTACT)
        }
        else{
            addFragment(ContactListFragment(), R.id.layout_fragment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_REQUEST_CONTACT -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    super.onPostResume()
                    addFragment(ContactListFragment(), R.id.layout_fragment)
                }
                else{
                    finish()
                }

            }
        }
    }

}
