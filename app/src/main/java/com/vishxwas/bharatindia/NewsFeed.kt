package com.vishxwas.bharatindia

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_meme_page.*
import kotlinx.android.synthetic.main.activity_news_feed.*
import newsItemClicked
import newsListAdapter

class NewsFeed : AppCompatActivity(), newsItemClicked {

    private lateinit var mAdapter: newsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)
        recyclerView.layoutManager = LinearLayoutManager(this)
        data()
        mAdapter = newsListAdapter(this)
        recyclerView.adapter = mAdapter
    }
    private fun data() {
        var head = "top-headlines"
        var everything = "everything"
        var country = "us"
//        val url = "https://newsapi.org/v2/$everything?country=$country&apiKey=48c5065ca0e64f3793cd3f1f0d3d96bc"
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=48c5065ca0e64f3793cd3f1f0d3d96bc"

        val jsonObjectRequest = object :JsonObjectRequest(Request.Method.GET, url, null,

            { response ->
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")

                    )
                    newsArray.add(news)
                }

                mAdapter.updateItems(newsArray)

            },
            { _ ->

            })

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onClick(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }
}