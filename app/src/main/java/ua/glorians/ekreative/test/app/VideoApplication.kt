package ua.glorians.ekreative.test.app

import android.app.Application
import ua.glorians.ekreative.test.app.data.database.DatabaseYT
import ua.glorians.ekreative.test.app.data.repository.RepositoryVideo

class VideoApplication: Application() {
    val database by lazy { DatabaseYT(this) }
    val repository by lazy { RepositoryVideo(database) }
}