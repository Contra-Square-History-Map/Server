package afrock.dance.map.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Image(
    @Column(nullable = false) var path: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
