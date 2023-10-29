package afrock.dance.map.entity

import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Sample(
    @TextIndexed var title: String,
    var path: String,
)
