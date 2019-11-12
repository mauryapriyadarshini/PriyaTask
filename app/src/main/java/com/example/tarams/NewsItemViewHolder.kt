package com.example.tarams

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tarams.model.Article
import kotlinx.android.synthetic.main.news_item_view_holder.view.*

class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Article,
        param: NewsRecyclerViewAdapter.CallBack
    ) {
        Glide.with(itemView.context)
            .asDrawable()
            .load(item.urlToImage)
            .into(itemView.articleImageView)

        itemView.title.text = item.title
        itemView.date.text = DateUtil().dateFormat(item.publishedAt)

        itemView.setOnClickListener {
            param.clickCallBack(item)
        }
    }
}