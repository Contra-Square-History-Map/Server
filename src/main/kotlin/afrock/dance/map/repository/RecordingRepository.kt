package afrock.dance.map.repository

import afrock.dance.map.entity.Recording
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RecordingRepository : CrudRepository<Recording, Long> {
    @Query(
        value = """
    SELECT r.* 
    FROM recording r
    JOIN recording_search rs ON r.id = rs.id
    WHERE (rs.body ILIKE '%' || :searchTerm || '%') OR (rs.document @@ plainto_tsquery('english', :searchTerm)) 
    """,
        nativeQuery = true,
    )
    fun search(@Param("searchTerm") searchTerm: String): List<Recording>
}
