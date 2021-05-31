package ua.glorians.ekreative.test.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.glorians.ekreative.test.app.data.model.VideoDetailsYT
import ua.glorians.ekreative.test.app.data.network.RetrofitClient

class DetailsVideoViewModel : ViewModel() {

    val video = MutableLiveData<VideoDetailsYT?>()

    fun getVideo(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.youtubeAPI.getDetailsVideoByID(id)
            if (response.isSuccessful) {
                val resultVideo = response.body()?.listResult?.get(0)
                video.postValue(resultVideo)
            }
        }
    }


}