package ua.glorians.ekreative.test.app.data.repository

import kotlinx.coroutines.flow.Flow
import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.network.RetrofitClient

class RepositoryVideo(
    private val db: DatabaseYT
) {

    private val videoDao = db.getVideoYTDao()
    val allVideosYT: Flow<List<VideoWithSnippetAndThumbnail>> = videoDao.getVideosWithSnippetAndThumbnail()

        //NETWORK
    suspend fun getVideosFromYoutube() =
        RetrofitClient.youtubeAPI.getVideosChannel()

    suspend fun getDetailsVideoFromYoutube(id: String) =
        RetrofitClient.youtubeAPI.getDetailsVideoByID(id)

    //DATABASE
    suspend fun upsertVideoYT(videoYT: VideoYT) {
        videoDao.insertVideo(videoYT)
    }

    suspend fun upsertListVideosYT(list: List<VideoYT>) {
        videoDao.insertVideos(list)
    }

    suspend fun upsertSnippet(snippetYT: SnippetYT) {
        videoDao.insertSnippet(snippetYT)
    }

    suspend fun upsertThumbnail(thumbnailsYT: ThumbnailsYT) {
        videoDao.insertThumbnail(thumbnailsYT)
    }

    suspend fun getVideoFromDatabase() =
        videoDao.getVideosWithSnippetAndThumbnail()

    suspend fun getThumbnails() =
        videoDao.getThumbnails()

    suspend fun getSnippetsFromDatabase() =
        videoDao.getSnippetAndThumbnail()

    suspend fun deleteVideoYT(videoYT: VideoYT) = db.getVideoYTDao().deleteVideo(videoYT)
}