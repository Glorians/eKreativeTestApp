package ua.glorians.ekreative.test.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo

class VideoViewModelFactory(private val repository: RepositoryVideo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListVideosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListVideosViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailsVideoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsVideoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}