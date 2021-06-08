package ua.glorians.ekreative.test.app.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoYT
import ua.glorians.ekreative.test.app.data.model.relations.VideoWithSnippetAndThumbnail

fun showSnack(view: View?, message: String) {
    view?.let {Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()}
}