package ua.glorians.ekreative.test.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.glorians.ekreative.test.app.data.database.dao.VideoYoutubeDao
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoYT

@Database(
    entities =
    [
        VideoYT::class, SnippetYT::class,
        ThumbnailsYT::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DatabaseYT: RoomDatabase() {

    abstract fun getVideoYTDao(): VideoYoutubeDao
    
    companion object {
        @Volatile
        private var instance: DatabaseYT? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseYT::class.java,
                "video_yt_db.db"
            ).build()
    }
}