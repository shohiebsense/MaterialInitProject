package com.shohiebsense.constraintlayoutexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.view.KeyEvent
import android.view.View
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kotlinx.android.synthetic.main.activity_scan_qr_code.*

class ScanQrCodeActivity : BaseActivity(), DecoratedBarcodeView.TorchListener {

    val REQUEST_CAMERA_PERMISSION = 1
    lateinit var captureManager : CaptureManager
    var toggleFlash = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr_code)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        toolbar.title = getString(R.string.scan_qr_code)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.elevation = 0f
        }
        val layout = window.attributes
        layout.screenBrightness = 1f
        window.attributes = layout


        captureManager = CaptureManager(this, barcode_scan)
        captureManager.initializeFromIntent(intent, savedInstanceState)
        captureManager.decode()
        requestStorageAndCameraPermission()



        if (!hasFlash()) {
            button_flash.setVisibility(View.GONE)
        }
        button_flash.setOnClickListener {
            switchFlashlight()
        }
        barcode_scan.setTorchListener(this)

    }

    fun switchFlashlight() {
        if (!toggleFlash) {
            barcode_scan.setTorchOn()
        } else {
            barcode_scan.setTorchOff()
        }
        toggleFlash = !toggleFlash
    }

    private fun hasFlash(): Boolean {
        return applicationContext.packageManager
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }


    override fun onPause() {
        super.onPause()
        captureManager.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        captureManager.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return barcode_scan.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    override fun onTorchOn() {

    }

    override fun onTorchOff() {
    }

    private fun requestStorageAndCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)){

            }
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        }
        else{
            captureManager.onResume()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults[0] != PackageManager.PERMISSION_GRANTED)) {
                finish()
                return
            }
            captureManager.onResume()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
