package ua.glorians.ekreative.test.app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.VideoID
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.SnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail

@Dao
interface VideoYoutubeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoYT: VideoYT)

    @Delete
    suspend fun deleteVideo(videoYT: VideoYT)

    @Transaction
    @Query("SELECT * FROM video_youtube_table")
    suspend fun getVideosWithSnippetAndThumbnail(): List<VideoWithSnippetAndThumbnail>

}