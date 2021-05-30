package ua.glorians.ekreative.test.app.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.glorians.ekreative.test.app.BuildConfig
import ua.glorians.ekreative.test.app.data.network.api.YoutubeAPI

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    lateinit var youtubeAPI: YoutubeAPI


    init {
        configureRetrofit()
    }

    private fun configureRetrofit() {
        if (retrofit == null && okHttpClient == null) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            youtubeAPI = retrofit!!.create(YoutubeAPI::class.java)
        }
    }

}