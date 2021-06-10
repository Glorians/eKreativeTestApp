package ua.glorians.ekreative.test.app.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ua.glorians.ekreative.test.app.BuildConfig
import ua.glorians.ekreative.test.app.data.model.ResponseListVideo
import ua.glorians.ekreative.test.app.data.model.ResponseVideoDetails

interface YoutubeAPI {

    //List Video From Channel
    @GET("search?")
    suspend fun getVideosChannel(

        @Query("key")
        key: String = KEY,

        @Query("channelId")
        channel: String = YOUTUBE_CHANNEL_ID,

        @Query("part")
        part: String =
            "snippet",

        @Query("maxResults")
        maxResult: Int = 500,

        @Query("order")
        order: String = "date",

    ): Response<ResponseListVideo>

    //Details Video
    @GET("videos?")
    suspend fun getDetailsVideoByID(
        @Query("id")
        id: String,

        @Query("key")
        key: String = KEY,

        @Query("part")
        parts: List<String> = listOf("statistics", "snippet")

    ) : Response<ResponseVideoDetails>

    companion object {
        var KEY = BuildConfig.YOUTUBE_API_KEY
        const val YOUTUBE_CHANNEL_ID = "UCP_IYZTiqbmUqmI3KXHIEoQ"
    }
}