package com.example.tarams.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL: String = "https://newsapi.org/v2/"
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().addHeader(
                    "Authorization",
                    "Bearer c4285fe8f70d4b16b140f850bd83771c"
                ).build()
            )
        }.build()

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }
            return retrofit
        }
    }
}