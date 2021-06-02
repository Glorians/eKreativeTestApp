package ua.glorians.ekreative.test.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.model.*
import ua.glorians.ekreative.test.app.data.repository.VideoRepository
import ua.glorians.ekreative.test.app.databinding.ActivityMainBinding
import ua.glorians.ekreative.test.app.utils.showSnack

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {

    }

    private fun initFunc() {
        testDatabase()
    }

    private fun testDatabase() {
        val repository = VideoRepository(DatabaseYT(this))
        CoroutineScope(Dispatchers.IO).launch {
//            repository.upsertVideoYT(getVideoFake())
            val videos = repository.getVideoFromDatabase()
            withContext(Dispatchers.Main) {
                videos[0].snippetAndThumbnail?.snippet?.description?.let {
                    showSnack(binding.root,
                        it
                    )
                }
            }
        }
    }


    private fun getVideoFake(): VideoYT {
        return VideoYT(
            videoID = VideoID("213"),
            snippet = SnippetYT(
                VideoID("213"),
                "2021",
                "Lol",
                "hello",
                ThumbnailsYT(
                    "2020",
                    ThumbnailType("default"),
                    ThumbnailType("medium"),
                    ThumbnailType("hight")
                ),
                "2020"
            )
        )
    }

}