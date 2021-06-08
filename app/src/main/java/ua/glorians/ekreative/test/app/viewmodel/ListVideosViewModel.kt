package ua.glorians.ekreative.test.app.viewmodel


import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail
import ua.glorians.ekreative.test.app.data.network.RetrofitClient
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo

class ListVideosViewModel(private val repository: RepositoryVideo) : ViewModel() {

    val allVideos: LiveData<List<VideoWithSnippetAndThumbnail>> = repository.allVideosYT.asLiveData()

    init {
        viewModelScope.launch {
            val response = RetrofitClient.youtubeAPI.getVideosChannel()
            if (response.isSuccessful) {
                val result = response.body()
                if (result?.listVideosYT?.isNotEmpty() == true) {
                    repository.upsertListVideosYT(result.listVideosYT)
                }
            }
        }
    }

//    fun loadVideos() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = RetrofitClient.youtubeAPI.getVideosChannel() //Get video
//            if (response.isSuccessful) {
//                withContext(Dispatchers.Main) {
//                    listVideos.postValue(response.body()) //Post to liveData videos
//                }
//            }
//        }
//    }
//
//    fun getVideos() {
//        viewModelScope.launch {
//            val response =
//        }
//    }
}