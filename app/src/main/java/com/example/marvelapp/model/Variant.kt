package com.example.marvelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Variant(
    val name: String,
    val resourceURI: String
): Parcelable