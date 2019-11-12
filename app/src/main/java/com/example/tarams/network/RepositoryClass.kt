package com.example.tarams.network

import androidx.lifecycle.MutableLiveData
import com.example.tarams.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryClass {

    private var urlInterface: URLInterface =
        RetrofitInstance.getClient()?.create(URLInterface::class.java)!!

    companion object {
        private object SingletonHelper {
            internal val INSTANCE = RepositoryClass()
        }

        fun getInstance(): RepositoryClass {
            return SingletonHelper.INSTANCE
        }
    }

    fun getHeadLines(country: String, page: Int, pageSize: Int): MutableLiveData<NewsResponse> {
        val news: MutableLiveData<NewsResponse> = MutableLiveData()
        urlInterface.getHeadlines(country, page, pageSize).enqueue(object :
            Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.body() != null) {
                    news.value = response.body()
                }
            }
            override fun onFailure(
                call: Call<NewsResponse>,
                t: Throwable
            ) {
               news.postValue(null)
            }
        })
        return news
    }
}