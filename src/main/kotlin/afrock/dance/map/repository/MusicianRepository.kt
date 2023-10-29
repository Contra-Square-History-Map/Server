package afrock.dance.map.repository

import afrock.dance.map.entity.Musician
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicianRepository : MongoRepository<Musician, String> {
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Musician?

//    fun findByName(firstName: String, lastName: String): Musician? {
//        return findByFirstNameAndLastName(firstName, lastName)
//    }
}