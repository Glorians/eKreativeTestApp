package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class ResultVideoDetails(
    @SerializedName("items")
    val listResult: List<VideoDetailsYT>
)