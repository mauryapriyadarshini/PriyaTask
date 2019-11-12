package com.example.tarams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tarams.model.Article
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {
    private var item: Article? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        item = intent.getParcelableExtra("object")

        item?.let {
            Glide.with(this).asDrawable().load(it.urlToImage).into(articleImageView)
            newsTitle.text = it.title
            date.text = DateUtil().dateFormat(it.publishedAt)
            newsDescription.text = it.content

        }

    }
}
