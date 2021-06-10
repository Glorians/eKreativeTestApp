package ua.glorians.ekreative.test.app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoID
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.SnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail

@Dao
interface VideoYoutubeDao {

    //VideoYT Operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoYT: VideoYT)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(listVideos: List<VideoYT>)

    @Delete
    suspend fun deleteVideo(videoYT: VideoYT)

    //VideoYT MultiOperations
    @Transaction
    @Query("SELECT * FROM video_youtube_table ORDER BY publishTime DESC")
    fun getVideosWithSnippetAndThumbnail(): Flow<List<VideoWithSnippetAndThumbnail>>

    @Transaction
    @Query("SELECT * FROM video_youtube_table WHERE video_id = :id")
    fun getVideoByVideoID(id: VideoID): LiveData<VideoWithSnippetAndThumbnail>

    //Snippet Operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSnippet(snippetYT: SnippetYT)

    @Delete
    suspend fun deleteSnippet(snippetYT: SnippetYT)

    //Snippet MultiOperations
    @Transaction
    @Query("SELECT * FROM snippet_table")
    suspend fun getSnippetsAndThumbnails() : List<SnippetAndThumbnail>

    @Transaction
    @Query("SELECT * FROM snippet_table WHERE publishTime = :timeID AND publishTimeID = :timeID")
    suspend fun getSnippetByID(timeID: String): SnippetAndThumbnail

    //Thumbnails Operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThumbnail(thumbnailsYT: ThumbnailsYT)

    @Delete
    suspend fun deleteThumbnails(thumbnailsYT: ThumbnailsYT)

    @Query("SELECT * FROM thumbnails_table")
    suspend fun getThumbnails(): List<ThumbnailsYT>

    @Query("SELECT * FROM thumbnails_table WHERE publishTimeID = :timeID")
    suspend fun getThumbnailsByTimeID(timeID: String): ThumbnailsYT
}