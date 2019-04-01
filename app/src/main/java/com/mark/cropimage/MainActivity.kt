package com.mark.cropimage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img_profile.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK) {
            when (requestCode) {
                0 -> {
                    cropImage()
                }
                1 -> {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    img_profile.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun openCamera() {
        startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            val dir = "${Environment.getExternalStorageDirectory()}/app/image/01"
            imageUri = Uri.fromFile(File(dir))
            Log.e(TAG, "uri path: ${imageUri?.path}")
            this.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }, 0)
    }

    private fun cropImage() {

    }
}
