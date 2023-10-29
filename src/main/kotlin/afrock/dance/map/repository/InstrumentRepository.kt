package afrock.dance.map.repository

import afrock.dance.map.entity.Instrument
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstrumentRepository : CrudRepository<Instrument, String> {
    fun findByName(name: String): Instrument?
}