package com.example.tarams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tarams.model.Article

class NewsRecyclerViewAdapter(private var param: CallBack) : RecyclerView.Adapter<NewsItemViewHolder>() {
    private var newsList = mutableListOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_view_holder,parent,false)
        return NewsItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return newsList.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item,param)
    }

    fun updateNewsList(list: MutableList<Article>) {
        val diffCallback = RatingDiffCallback(newsList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        newsList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    interface CallBack{
        fun clickCallBack(item: Article)
    }
}

