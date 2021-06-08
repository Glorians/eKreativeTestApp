package ua.glorians.ekreative.test.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "video_youtube_table",
    primaryKeys = ["video_id"]
)
data class VideoYT(

    @ColumnInfo(name = "video_id")
    @SerializedName("id")
    val videoID: VideoID,

    @SerializedName("snippet")
    @Embedded
    val snippet: SnippetYT,

)