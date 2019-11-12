package com.example.tarams.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    var id: String? = null,
    var name: String? = null
):Parcelable
