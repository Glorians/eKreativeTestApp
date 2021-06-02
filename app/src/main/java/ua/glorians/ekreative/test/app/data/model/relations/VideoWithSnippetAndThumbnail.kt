package ua.glorians.ekreative.test.app.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.VideoYT

data class VideoWithSnippetAndThumbnail(
    @Embedded val video: VideoYT,
    @Relation(
        entity = SnippetYT::class,
        parentColumn = "videoID",
        entityColumn = "snippet_video_id"
    )
    val snippetAndThumbnail: SnippetAndThumbnail
)