package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailsYT(

    @SerializedName("default")
    val default: ThumbnailType,

    @SerializedName("medium")
    val medium: ThumbnailType,

    @SerializedName("high")
    val high: ThumbnailType
)
