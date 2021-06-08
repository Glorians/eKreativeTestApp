package ua.glorians.ekreative.test.app

import kotlinx.coroutines.runBlocking
import org.junit.Test
import ua.glorians.ekreative.test.app.data.network.RetrofitClient


class TestingYoutubeAPI {


    @Test
    fun testLoadVideosFromChannel() {
        runBlocking {
            val response = RetrofitClient.youtubeAPI.getVideosChannel()
            if (response.isSuccessful) {
                assert(response.isSuccessful)
            }
        }
    }


}