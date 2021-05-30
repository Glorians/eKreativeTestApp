package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class SnippetYT(

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsYT,

    @SerializedName("publishTime")
    val publishTime: String
)