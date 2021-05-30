package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailType(
    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int
)