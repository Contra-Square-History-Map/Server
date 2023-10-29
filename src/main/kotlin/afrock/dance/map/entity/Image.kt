package afrock.dance.map.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Image(
    var path: String,
)
