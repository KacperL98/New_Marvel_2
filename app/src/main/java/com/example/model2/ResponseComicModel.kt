package com.example.desafiomobilemarvel.service.model.comic

import android.os.Parcelable
import com.example.marvelapp.model.Data
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseComicModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
): Parcelable


