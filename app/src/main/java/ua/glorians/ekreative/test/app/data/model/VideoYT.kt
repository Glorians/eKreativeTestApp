package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class VideoYT(

    @SerializedName("id")
    val videoID: VideoID,

    @SerializedName("snippet")
    val snippet: SnippetYT,

)