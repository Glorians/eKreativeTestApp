package ua.glorians.ekreative.test.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "snippet_table",
    primaryKeys = ["snippet_video_id"]
)
data class SnippetYT(

    @ColumnInfo(name = "snippet_video_id")
    var videoID: VideoID,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnails")
    @Embedded
    val thumbnails: ThumbnailsYT,

    @SerializedName("publishTime")
    var publishTime: String
)