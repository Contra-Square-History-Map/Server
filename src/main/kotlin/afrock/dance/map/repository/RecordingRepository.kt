package afrock.dance.map.repository

import afrock.dance.map.entity.Recording
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RecordingRepository : MongoRepository<Recording, String> {
    //    @Query(
//        value = """
//    SELECT r.*
//    FROM recording r
//    JOIN recording_search rs ON r.id = rs.id
//    WHERE (rs.body ILIKE '%' || :searchTerm || '%') OR (rs.document @@ plainto_tsquery('english', :searchTerm))
//    """,
//        nativeQuery = true,
//    )
    @Query("{'\$text': {'\$search': ?0}}")
    fun search(searchTerm: String): List<Recording>
}
