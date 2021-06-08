package ua.glorians.ekreative.test.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.*
import org.junit.After
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
    private var appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun createRepository() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        repository = RepositoryVideo(DatabaseYT(appContext))
    }

    @Test
    @Throws(Exception::class)
    fun testWriteAndReadVideoYT_ToDatabase() {
        val liveValue = MutableLiveData<VideoWithSnippetAndThumbnail>()

        runBlocking(Dispatchers.Main) {

            repository.upsertVideoYT(getVideoYT_Fake()) // Insert Video
            val videos = repository.allVideosYT.asLiveData() // SELECT ALL videos

            videos.observeForever {
                if (it.isNotEmpty()) {
                    val video = it[0]
                    liveValue.postValue(video)
                    println("video: $video")
                }
                else fail("list is empty")
            }

            liveValue.observeForever {
                assertEquals(getSnippetYT_Fake().title, it.snippetAndThumbnail.snippet.title)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testWriteAndReadSnippetYT() {
        val liveValue = MutableLiveData<SnippetAndThumbnail>()

        runBlocking(Dispatchers.Main) {

            repository.upsertSnippet(getSnippetYT_Fake()) // Insert Snippet
            val snippets = repository.getSnippetsFromDatabase() // SELECT ALL snippets

            if (snippets.isNotEmpty()) {
                val snippet = snippets[0]
                liveValue.postValue(snippet)
                println("snippet: $snippet")
            }
            else fail("list is empty")

            liveValue.observeForever {
                assertEquals(getSnippetYT_Fake().title, it.snippet.title)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testWriteAndReadThumbnailYT() {
        val liveValue = MutableLiveData<ThumbnailsYT>()

        runBlocking(Dispatchers.Main) {

            repository.upsertThumbnail(getThumbnailYT_Fake()) // Insert Thumbnail
            val thumbnails = repository.getThumbnails() // SELECT ALL thumbnails

            if (thumbnails.isNotEmpty()) {
                val thumbnail = thumbnails[0]
                liveValue.postValue(thumbnail)
                println("thumbnail: $thumbnail")
            }
            else fail("list is empty")

            liveValue.observeForever {
                assertEquals(getThumbnailYT_Fake().mediumSize.url, it.mediumSize.url)
            }
        }
    }

    @After
    fun clearDatabase() {

    }

    private fun getVideoYT_Fake(): VideoYT {
        return VideoYT(
            videoID = getVideoID_Fake(),
            snippet = getSnippetYT_Fake()
        )
    }

    private fun getSnippetYT_Fake(): SnippetYT {
        return SnippetYT(
            videoID = getVideoID_Fake(),
            title = "Testing Video",
            description = "Description video la-la-la-la-la",
            thumbnails = getThumbnailYT_Fake(),
            publishTime = "2020.07.22"
        )
    }

    private fun getThumbnailYT_Fake(): ThumbnailsYT {
        return ThumbnailsYT(
            publishTime = "2020.07.22",
            defaultSize = ThumbnailType("default image"),
            mediumSize = ThumbnailType("medium image"),
            highSize = ThumbnailType("high image")
        )
    }

    private fun getVideoID_Fake(): VideoID {
        return VideoID("ihtZV0sHQ94")
    }

}