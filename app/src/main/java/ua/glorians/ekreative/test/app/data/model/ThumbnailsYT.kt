package ua.glorians.ekreative.test.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "thumbnails_table",
    primaryKeys = ["publishTimeID"]
)
data class ThumbnailsYT(

    @ColumnInfo(name = "publishTimeID")
    var publishTime: String,

    @SerializedName("default")
    val defaultSize: ThumbnailType,

    @SerializedName("medium")
    val mediumSize: ThumbnailType,

    @SerializedName("high")
    val highSize: ThumbnailType
)
