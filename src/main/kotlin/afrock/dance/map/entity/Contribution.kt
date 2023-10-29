package afrock.dance.map.entity

import afrock.dance.map.Contribution as ContributionMessage
import afrock.dance.map.contribution as contributionMessage
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Contribution(
    @DBRef var musician: Musician,
    @DBRef var instrument: Instrument,
) {
    fun toProto(): ContributionMessage {
        return contributionMessage {
            musician = this@Contribution.musician.toProto()
            instrument = this@Contribution.instrument.name
        }
    }
}
