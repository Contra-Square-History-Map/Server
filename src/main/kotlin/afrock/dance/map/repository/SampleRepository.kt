package afrock.dance.map.repository

import afrock.dance.map.entity.Sample
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository : CrudRepository<Sample, Long> {
}