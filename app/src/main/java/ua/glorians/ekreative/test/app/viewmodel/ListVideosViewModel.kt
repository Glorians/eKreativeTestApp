package ua.glorians.ekreative.test.app.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.glorians.ekreative.test.app.data.model.ListVideos
import ua.glorians.ekreative.test.app.data.network.RetrofitClient

class ListVideosViewModel : ViewModel() {

    val listVideos = MutableLiveData<ListVideos>()

    fun loadVideos() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.youtubeAPI.getVideosChannel() //Get video
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    listVideos.postValue(response.body()) //Post to liveData videos
                }
            }
        }
    }
}