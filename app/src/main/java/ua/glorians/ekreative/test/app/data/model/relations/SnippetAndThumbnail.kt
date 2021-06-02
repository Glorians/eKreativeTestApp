package ua.glorians.ekreative.test.app.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT

data class SnippetAndThumbnail(
    @Embedded
    val snippet: SnippetYT,
    @Relation(
        entity = ThumbnailsYT::class,
        parentColumn = "publishTime",
        entityColumn = "publishTimeID"
    )
    val thumbnailsYT: ThumbnailsYT
)