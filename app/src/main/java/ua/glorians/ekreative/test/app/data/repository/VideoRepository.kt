package ua.glorians.ekreative.test.app.data.repository

import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.network.RetrofitClient

class VideoRepository(
    private val db: DatabaseYT
) {

    suspend fun getVideosFromYoutube(channelID: String) =
        RetrofitClient.youtubeAPI.getVideosChannel(channelID)

    suspend fun getVideoFromDatabase() =
        db.getVideoYTDao().getVideosWithSnippetAndThumbnail()

    suspend fun upsertVideoYT(videoYT: VideoYT) {
        db.getVideoYTDao().insertVideo(videoYT)
    }

    suspend fun deleteVideoYT(videoYT: VideoYT) = db.getVideoYTDao()
}