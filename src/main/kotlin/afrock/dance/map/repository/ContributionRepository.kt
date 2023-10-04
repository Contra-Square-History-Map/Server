package afrock.dance.map.repository

import afrock.dance.map.entity.Contribution
import afrock.dance.map.entity.Instrument
import afrock.dance.map.entity.Musician
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContributionRepository : CrudRepository<Contribution, Long> {
    fun findByMusicianAndInstrument(musician: Musician, instrument: Instrument): Contribution?
}