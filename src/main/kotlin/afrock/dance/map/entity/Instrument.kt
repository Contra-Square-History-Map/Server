package afrock.dance.map.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Instrument(
    @TextIndexed var name: String,
    @Id var id: String? = null,
)
