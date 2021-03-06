package ua.glorians.ekreative.test.app.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo

class ListVideosViewModel(private val repository: RepositoryVideo): ViewModel() {

    var allVideos: LiveData<List<VideoWithSnippetAndThumbnail>>? = null

    fun initListVideos() {
        viewModelScope.launch {
            val response = repository.getVideosFromYoutube()
            if (response.isSuccessful) {
                val listVideos = response.body()?.listVideosYT
                listVideos?.forEach { video ->
                    video.snippet.videoID = video.videoID
                    video.snippet.thumbnails.publishTime = video.snippet.publishTime
                    repository.upsertThumbnail(video.snippet.thumbnails)
                    repository.upsertSnippet(video.snippet)
                    repository.upsertVideoYT(video)
                }
            }
        }
        allVideos = repository.allVideosYT
    }
}