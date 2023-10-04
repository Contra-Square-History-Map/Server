package afrock.dance.map.entity

import afrock.dance.map.recording as recordingMessage
import afrock.dance.map.sample as sampleMessage
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.util.Date

@Entity
data class Recording(
    @Column(nullable = false) var title: String,
    @Column(nullable = false) var band: String,
    @Column(nullable = false) @Temporal(TemporalType.TIMESTAMP) var releaseDate: Date,
    @Column(nullable = false) var latitude: Float,
    @Column(nullable = false) var longitude: Float,
    @Column(nullable = false) var location: String,
    @Column(nullable = false) var country: String,
    @Column(nullable = false) var state: String,
    @Column(nullable = false) var city: String,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var contributions: Set<Contribution>,
    @Column(nullable = false) var lpRelease: Boolean,
    @Column(nullable = false) var cassetteRelease: Boolean,
    @Column(nullable = false) var cdRelease: Boolean,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var comments: Set<Comment>,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var samples: Set<Sample>,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) var images: Set<Image>,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    fun toProto() = recordingMessage {
        id = this@Recording.id ?: -1
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
        id = this@Recording.id ?: -1
        title = this@Recording.title
        releaseTime = this@Recording.releaseDate.time
        latitude = this@Recording.latitude
        longitude = this@Recording.longitude
        location = this@Recording.location
    }
}
