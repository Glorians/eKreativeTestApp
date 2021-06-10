package ua.glorians.ekreative.test.app

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.*
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.model.*
import ua.glorians.ekreative.test.app.data.model.relations.SnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class TestingDatabase {
    private lateinit var repository: RepositoryVideo
    private lateinit var appContext: Context

    @Before
    fun createRepository() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        repository = RepositoryVideo(DatabaseYT(appContext))
    }

    @Test // Video Youtube
    @Throws(Exception::class)
    fun testWriteAndReadVideoYT_ToDatabase() {
        val liveValue = MutableLiveData<VideoWithSnippetAndThumbnail>()

        runBlocking(Dispatchers.Main) {

            repository.upsertVideoYT(videoFake) // Insert Video
            val videos = repository.getVideoFromDatabase(videoID_Fake) // SELECT ALL videos

            videos.observeForever { video ->
                liveValue.postValue(video)
                println("video: $video")
            }
            liveValue.observeForever {
                assertEquals(snippetFake.title, it.snippetAndThumbnail.snippet.title)
            }
        }
    }

    @Test // Snippet With Thumbnail
    @Throws(Exception::class)
    fun testWriteAndReadSnippetYT() {
        val liveValue = MutableLiveData<SnippetAndThumbnail>()
        runBlocking((Dispatchers.Main)) {

            repository.upsertSnippet(snippetFake) // Insert Snippet
            val snippet =
                repository.getSnippetByID(snippetFake.publishTime) // SELECT snippet
            liveValue.postValue(snippet)
            liveValue.observeForever {
                println("snippet: $it")
                assertEquals(snippetFake.title, it.snippet.title)
            }
        }
    }

    @Test // Thumbnail
    @Throws(Exception::class)
    fun testWriteAndReadThumbnailYT() {
        val liveValue = MutableLiveData<ThumbnailsYT>()
        runBlocking(Dispatchers.Main) {
            repository.upsertThumbnail(thumbnailFake) // Insert Thumbnail
            val thumbnail =
                repository.getThumbnailByID(thumbnailFake.publishTime) // SELECT thumbnail
            liveValue.postValue(thumbnail)

            liveValue.observeForever {
                println("thumbnail: $it")
                assertEquals(thumbnailFake.mediumSize.url, it.mediumSize.url)
            }
        }
    }



    companion object {

        @AfterClass// Clear Database
        fun clearDatabase() {
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val repository = RepositoryVideo(DatabaseYT(appContext))
            runBlocking {
                repository.deleteSnippet(snippetFake)
                repository.deleteThumbnail(thumbnailFake)
                repository.deleteVideoYT(videoFake)
            }
        }
        // Fakes Objects //
        private val videoID_Fake = VideoID("ihtZV0sHQ94")

        private val thumbnailFake =
            ThumbnailsYT(
                publishTime = "2020.07.22",
                defaultSize = ThumbnailType("default image"),
                mediumSize = ThumbnailType("medium image"),
                highSize = ThumbnailType("high image")
            )

        private val snippetFake =
            SnippetYT(
                videoID = videoID_Fake,
                title = "Testing Video",
                description = "Description video la-la-la-la-la",
                thumbnails = thumbnailFake,
                publishTime = "2020.07.22"
            )

        private val videoFake =
            VideoYT(
                videoID = videoID_Fake,
                snippet = snippetFake
            )
    }
}