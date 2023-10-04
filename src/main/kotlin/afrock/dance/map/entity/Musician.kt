package afrock.dance.map.entity

import afrock.dance.map.Musician as MusicianMessage
import afrock.dance.map.musician as musicianMessage
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Musician(
    @Column(nullable = false) var firstName: String,
    @Column(nullable = false) var lastName: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    fun toProto(): MusicianMessage {
        return musicianMessage {
            id = this@Musician.id ?: -1
            firstName = this@Musician.firstName
            lastName = this@Musician.lastName
        }
    }
}
