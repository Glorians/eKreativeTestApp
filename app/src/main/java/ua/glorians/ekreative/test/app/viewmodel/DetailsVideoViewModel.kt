package ua.glorians.ekreative.test.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.glorians.ekreative.test.app.data.model.VideoDetailsYT
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo

class DetailsVideoViewModel(val repository: RepositoryVideo) : ViewModel() {

    val video = MutableLiveData<VideoDetailsYT?>()

    fun initDetailsVideo(videoID: String) {
        viewModelScope.launch {
            val response = repository.getDetailsVideoFromYoutube(videoID)
            if (response.isSuccessful) {
                val result = response.body()
                if (result?.listResult?.isNotEmpty() == true) {
                    video.postValue(result.listResult[0])
                }
            }
        }
    }
}