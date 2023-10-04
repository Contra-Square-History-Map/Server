package afrock.dance.map.repository

import afrock.dance.map.entity.Image
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : CrudRepository<Image, Long> {
}