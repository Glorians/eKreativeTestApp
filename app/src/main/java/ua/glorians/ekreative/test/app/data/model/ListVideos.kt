package ua.glorians.ekreative.test.app.data.model

import com.google.gson.annotations.SerializedName

data class ListVideos(

    @SerializedName("nextPageToken")
    var nextPageToken: String,

    @SerializedName("items")
    var listVideosYT: List<VideoYT>
)