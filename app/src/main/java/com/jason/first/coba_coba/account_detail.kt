package com.jason.first.coba_coba

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide

class account_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)
        Glide.with(this).load(Uri.parse("file:///android_asset/Jason.jpg")).into(findViewById(R.id.jason_image))
    }
}
