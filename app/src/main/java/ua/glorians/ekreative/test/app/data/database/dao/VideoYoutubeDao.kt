package ua.glorians.ekreative.test.app.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.SnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail

@Dao
interface VideoYoutubeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoYT: VideoYT)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(listVideos: List<VideoYT>)

    @Delete
    suspend fun deleteVideo(videoYT: VideoYT)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSnippet(snippetYT: SnippetYT)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThumbnail(thumbnailsYT: ThumbnailsYT)

    @Query("SELECT * FROM thumbnails_table")
    suspend fun getThumbnails(): List<ThumbnailsYT>

    @Transaction
    @Query("SELECT * FROM snippet_table")
    suspend fun getSnippetAndThumbnail() : List<SnippetAndThumbnail>

    @Transaction
    @Query("SELECT * FROM video_youtube_table")
    fun getVideosWithSnippetAndThumbnail(): Flow<List<VideoWithSnippetAndThumbnail>>

}