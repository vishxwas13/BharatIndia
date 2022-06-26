package com.vishxwas.bharatindia

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_meme_page.*

class MemePage : AppCompatActivity() {
    private var currURL: String ?= null
    private val url = "https://meme-api.herokuapp.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_page)


        loadItem(url)
    }
    fun loadItem(url: String) {
        progressBar.visibility = View.VISIBLE

        val jasonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                currURL= response.getString("url")

                Glide.with(this).load(currURL).listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false                    }
                }).into(itemView)

            },
            {
                Toast.makeText(this,"No Internet!", Toast.LENGTH_LONG)
//                Toast.makeText(this,"Find your name $name",Toast.LENGTH_SHORT).show()
            })

        MySingleton.getInstance(this).addToRequestQueue(jasonObjectRequest)
    }

    fun next(view: android.view.View) {
        loadItem(url)
    }
    fun share(view: android.view.View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type= "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey , Checkout this, $currURL")
        val chooser = Intent.createChooser(intent,"Sharing...")
        startActivity(chooser)
    }
}