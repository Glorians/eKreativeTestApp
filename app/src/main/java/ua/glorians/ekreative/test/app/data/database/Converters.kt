package ua.glorians.ekreative.test.app.data.database

import androidx.room.TypeConverter
import ua.glorians.ekreative.test.app.data.model.ThumbnailType
import ua.glorians.ekreative.test.app.data.model.VideoID

class Converters {
    @TypeConverter
    fun fromThumbnailType(thumbnailType: ThumbnailType): String {
        return thumbnailType.url
    }

    @TypeConverter
    fun toThumbnailType(data: String): ThumbnailType {
        return ThumbnailType(data)
    }

    @TypeConverter
    fun fromVideoID(videoID: VideoID): String {
        return videoID.id
    }

    @TypeConverter
    fun toVideoID(data: String): VideoID {
        return VideoID(data)
    }
}