package afrock.dance.map.entity

import afrock.dance.map.Comment as CommentMessage
import afrock.dance.map.comment as commentMessage
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Comment(
    @Column(nullable = false) var author: String,
    @Column(nullable = false, length = 65535) var body: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    fun toProto(): CommentMessage {
        return commentMessage {
            id = this@Comment.id ?: -1
            author = this@Comment.author
            text = this@Comment.body
        }
    }
}
