package afrock.dance.map.entity

import afrock.dance.map.Contribution as ContributionMessage
import afrock.dance.map.contribution as contributionMessage
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(
        name = "contribution_uid",
        columnNames = ["musician_id", "instrument_id"]
    )],
    indexes = [Index(
        name = "contribution_index",
        columnList = "musician_id, instrument_id"
    )]
)
data class Contribution(
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(nullable = false) var musician: Musician,
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(nullable = false) var instrument: Instrument,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    fun toProto(): ContributionMessage {
        return contributionMessage {
            musician = this@Contribution.musician.toProto()
            instrument = this@Contribution.instrument.name
        }
    }
}
