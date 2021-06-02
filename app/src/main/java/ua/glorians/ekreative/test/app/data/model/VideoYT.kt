package ua.glorians.ekreative.test.app.data.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "video_youtube_table",
    primaryKeys = ["videoID"]
)
data class VideoYT(

    @SerializedName("id")
    val videoID: VideoID,

    @SerializedName("snippet")
    @Embedded
    val snippet: SnippetYT,

)