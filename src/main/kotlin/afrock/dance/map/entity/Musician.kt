package afrock.dance.map.entity

import afrock.dance.map.Musician as MusicianMessage
import afrock.dance.map.musician as musicianMessage
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Musician(
    @TextIndexed var firstName: String,
    @TextIndexed var lastName: String,
    @Id var id: String? = null
) {
    fun toProto(): MusicianMessage {
        return musicianMessage {
            id = this@Musician.id ?: ""
            firstName = this@Musician.firstName
            lastName = this@Musician.lastName
        }
    }
}
