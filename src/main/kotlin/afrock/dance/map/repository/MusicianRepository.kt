package afrock.dance.map.repository

import afrock.dance.map.entity.Musician
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicianRepository : CrudRepository<Musician, Long> {
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Musician?

//    fun findByName(firstName: String, lastName: String): Musician? {
//        return findByFirstNameAndLastName(firstName, lastName)
//    }
}