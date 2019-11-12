package com.example.tarams.model

import kotlinx.android.parcel.Parcelize

data class NewsResponse(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<Article>? = null
)
