package com.vishxwas.bharatindia

import MySingleton
import android.app.Notification.EXTRA_TEXT
import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getSupportActionBar().setBackgroundDrawable(new colorDrawable(getResources().getColor(R.color.share))

    }

    fun meme(view: android.view.View) {
        var intent = Intent(this,MemePage::class.java)
        startActivity(intent)
    }

    fun shopping(view: android.view.View) {
        Toast.makeText(this,"No internet! or Server error",Toast.LENGTH_LONG).show()
    }
    fun cricket(view: android.view.View) {
        Toast.makeText(this,"No internet! or Server error",Toast.LENGTH_LONG).show()
    }
    fun news(view: android.view.View) {
        var intent = Intent(this,NewsFeed::class.java)
        startActivity(intent)
    }

    fun covid(view: android.view.View) {
        Toast.makeText(this,"No internet! or Server error",Toast.LENGTH_LONG).show()
    }

    fun gallery(view: android.view.View) {
        var intent = Intent(this,BucketList::class.java)
        startActivity(intent)
        Toast.makeText(this,"No internet! or Server error",Toast.LENGTH_LONG).show()
    }

    fun culture(view: android.view.View) {
        Toast.makeText(this,"No internet! or Server error",Toast.LENGTH_LONG).show()
    }


}