package afrock.dance.map.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import afrock.dance.map.Comment as CommentMessage
import afrock.dance.map.comment as commentMessage

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Comment(
    @TextIndexed var author: String,
    @TextIndexed var body: String,
) {
    fun toProto(): CommentMessage {
        return commentMessage {
            author = this@Comment.author
            text = this@Comment.body
        }
    }
}
