package afrock.dance.map.configuration

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfiguration {
    @Bean
    fun mongoTemplate(client: MongoClient): MongoTemplate {
        return MongoTemplate(client, "ahandfortheband")
    }
}