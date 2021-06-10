package ua.glorians.ekreative.test.app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoID
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.network.RetrofitClient

class RepositoryVideo(
    private val db: DatabaseYT
) {

    private val videoDao = db.getVideoYTDao()
    val allVideosYT: LiveData<List<VideoWithSnippetAndThumbnail>> = getVideosFromDatabase().asLiveData()

    //NETWORK
    suspend fun getVideosFromYoutube() =
        RetrofitClient.youtubeAPI.getVideosChannel()

    suspend fun getDetailsVideoFromYoutube(id: String) =
        RetrofitClient.youtubeAPI.getDetailsVideoByID(id)

    //DATABASE//

    //VideoYT Operations---

    // Insert Video
    suspend fun upsertVideoYT(videoYT: VideoYT) {
        videoDao.insertVideo(videoYT)
    }

    // Get list videos from Database
    private fun getVideosFromDatabase() =
        videoDao.getVideosWithSnippetAndThumbnail()

    // Get video by videoID
    suspend fun getVideoFromDatabase(id: VideoID) =
        videoDao.getVideoByVideoID(id)

    // Delete video
    suspend fun deleteVideoYT(videoYT: VideoYT) =
        db.getVideoYTDao().deleteVideo(videoYT)

    //SnippetYT Operations---

    // Insert Snippet
    suspend fun upsertSnippet(snippetYT: SnippetYT) {
        videoDao.insertSnippet(snippetYT)
    }

    // Delete Snippet
    suspend fun deleteSnippet(snippetYT: SnippetYT) {
        videoDao.deleteSnippet(snippetYT)
    }

    // Get snippets witch thumbnails
    suspend fun getSnippetsFromDatabase() =
        videoDao.getSnippetsAndThumbnails()

    // Get Snippet
    suspend fun getSnippetByID(timeID: String) = videoDao.getSnippetByID(timeID)

    //ThumbnailsYT Operations---

    // Insert Thumbnail
    suspend fun upsertThumbnail(thumbnailsYT: ThumbnailsYT) {
        videoDao.insertThumbnail(thumbnailsYT)
    }

    // Delete Thumbnail
    suspend fun deleteThumbnail(thumbnailsYT: ThumbnailsYT) {
        videoDao.deleteThumbnails(thumbnailsYT)
    }

    // Get all list Thumbnails
    suspend fun getThumbnails() =
        videoDao.getThumbnails()

    // Get Thumbnail
    suspend fun getThumbnailByID(idTime: String) =
        videoDao.getThumbnailsByTimeID(idTime)
}