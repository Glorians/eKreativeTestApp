package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class VideoDetailsYT(

    @SerializedName("id")
    val id: String,

    @SerializedName("snippet")
    val snippet: SnippetYT,

    @SerializedName("statistics")
    val statistics: StatisticsYT

)