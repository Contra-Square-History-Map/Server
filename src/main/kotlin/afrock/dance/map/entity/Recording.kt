package afrock.dance.map.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date
import afrock.dance.map.recording as recordingMessage
import afrock.dance.map.sample as sampleMessage

@Document
data class Recording(
    @TextIndexed var title: String,
    @TextIndexed var band: String,
    var releaseDate: Date,
    var latitude: Float,
    var longitude: Float,
    @TextIndexed var location: String,
    @TextIndexed var country: String,
    @TextIndexed var state: String,
    @TextIndexed var city: String,
    var contributions: Set<Contribution>,
    var lpRelease: Boolean,
    var cassetteRelease: Boolean,
    var cdRelease: Boolean,
    var comments: Set<Comment>,
    var samples: Set<Sample>,
    var images: Set<Image>,
    @Id var id: String? = null
) {
    fun toProto() = recordingMessage {
        id = this@Recording.id ?: ""
        title = this@Recording.title
        band = this@Recording.band
        releaseTime = this@Recording.releaseDate.time
        latitude = this@Recording.latitude
        longitude = this@Recording.longitude
        location = this@Recording.location
        lpRelease = this@Recording.lpRelease
        cassetteRelease = this@Recording.cassetteRelease
        cdRelease = this@Recording.cdRelease
        comments.addAll(this@Recording.comments.map { comment -> comment.toProto() })
        samples.addAll(this@Recording.samples.map { sample ->
            sampleMessage {
                title = sample.title
                url = sample.path
            }
        })
        images.addAll(this@Recording.images.map { image -> image.path })
        contributions.addAll(this@Recording.contributions.map { contribution -> contribution.toProto() })
    }

    fun toProtoLite() = recordingMessage {
        id = this@Recording.id ?: ""
        title = this@Recording.title
        releaseTime = this@Recording.releaseDate.time
        latitude = this@Recording.latitude
        longitude = this@Recording.longitude
        location = this@Recording.location
    }
}
