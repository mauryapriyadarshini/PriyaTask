package com.example.tarams

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tarams.model.NewsResponse
import com.example.tarams.network.RepositoryClass

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private var newsList: MutableLiveData<NewsResponse> = MutableLiveData()
    private var totalPage:Int? = null

    fun getNewsLists(country: String, page: Int, pageSize: Int): MutableLiveData<NewsResponse> {
        newsList =  RepositoryClass.getInstance().getHeadLines(country,page,pageSize)
        totalPage = newsList.value?.totalResults
        return newsList
    }

    fun isLastPage() : Boolean {
        return totalPage == PAGE_SIZE
    }
}