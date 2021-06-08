package ua.glorians.ekreative.test.app.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ua.glorians.ekreative.test.app.data.model.SnippetYT
import ua.glorians.ekreative.test.app.data.model.ThumbnailsYT
import ua.glorians.ekreative.test.app.data.model.VideoYT

data class VideoWithSnippetAndThumbnail(
    @Embedded val video: VideoYT,
    @Relation(
        entity = SnippetYT::class,
        parentColumn = "video_id",
        entityColumn = "snippet_video_id"
    )
    val snippetAndThumbnail: SnippetAndThumbnail
) {
    fun toVideoYT(): VideoYT {
        val snippetYT = snippetAndThumbnail.snippet
        val thumbnail = snippetAndThumbnail.thumbnailsYT
        return VideoYT(
            videoID = video.videoID,
            snippet = SnippetYT(
                videoID = snippetYT.videoID,
                title = snippetYT.title,
                description = snippetYT.description,
                publishTime = snippetYT.publishTime,
                thumbnails = ThumbnailsYT(
                    publishTime = thumbnail.publishTime,
                    defaultSize = thumbnail.defaultSize,
                    mediumSize = thumbnail.mediumSize,
                    highSize = thumbnail.highSize
                )
            )
        )
    }
}