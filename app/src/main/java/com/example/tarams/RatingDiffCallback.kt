package com.example.tarams

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.tarams.model.Article

class RatingDiffCallback(private val oldList: List<Article>, private val newList: List<Article>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title === newList.get(newItemPosition).title
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, _, name) = oldList[oldPosition]
        val (_, _, name1) = newList[newPosition]

        return name == name1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}