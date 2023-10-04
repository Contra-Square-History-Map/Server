package afrock.dance.map

import afrock.dance.map.entity.Comment
import afrock.dance.map.entity.Contribution
import afrock.dance.map.entity.Instrument
import afrock.dance.map.entity.Musician
import afrock.dance.map.entity.Recording
import afrock.dance.map.entity.Sample
import afrock.dance.map.repository.CommentRepository
import afrock.dance.map.repository.ContributionRepository
import afrock.dance.map.repository.InstrumentRepository
import afrock.dance.map.repository.MusicianRepository
import afrock.dance.map.repository.RecordingRepository
import afrock.dance.map.repository.SampleRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.Calendar
import java.util.Date

@SpringBootApplication
class MapApplication {

//    @Bean
//    fun sampleData(
//        instrumentRepository: InstrumentRepository,
//        musicianRepository: MusicianRepository,
//        contributionRepository: ContributionRepository,
//        recordingRepository: RecordingRepository,
//        sampleRepository: SampleRepository,
//        commentRepository: CommentRepository
//    ): ApplicationRunner {
//        return ApplicationRunner {
//            // Load Instruments
//            listOf(
//                Instrument("fiddle"),
//                Instrument("piano"),
//                Instrument("triangle"),
//                Instrument("accordion"),
//                Instrument("synthesizer"),
//                Instrument("5-string banjo"),
//                Instrument("bass"),
//                Instrument("guitar"),
//                Instrument("ukulele"),
//                Instrument("ukulele banjo"),
//                Instrument("mandolin"),
//            ).also {
//                instrumentRepository.saveAll(it)
//            }
//
//            // Load Musicians
//            listOf(
//                Musician(
//                    "Barbara",
//                    "Greenberg"
//                ),
//                Musician(
//                    "Bob",
//                    "Pasquarello"
//                ),
//                Musician(
//                    "Bob",
//                    "Stein"
//                ),
//                Musician(
//                    "Paul",
//                    "Brown"
//                ),
//                Musician(
//                    "Dan",
//                    "Newhall"
//                ),
//                Musician(
//                    "Bruce",
//                    "Molsky"
//                ),
//                Musician(
//                    "Chester",
//                    "McMillian"
//                ),
//                Musician(
//                    "Mike",
//                    "Seeger"
//                ),
//                Musician(
//                    "Al",
//                    "Cantrell"
//                ),
//                Musician(
//                    "Emily",
//                    "Cantrell"
//                ),
//            ).also {
//                musicianRepository.saveAll(it)
//            }
//
//            // Load Recordings
//            with(
//                Recording(
//                    title = "A Band Named Bob",
//                    band = "A Band Named Bob",
//                    releaseDate = with(Calendar.getInstance()) {
//                        set(Calendar.YEAR, 1994)
//                        Date.from(toInstant())
//                    },
//                    contributions = setOfNotNull(
//                        Contribution(
//                            musicianRepository.findByFirstNameAndLastName(
//                                "Barbara",
//                                "Greenberg"
//                            )!!, instrumentRepository.findByName("fiddle")!!
//                        ).also { contributionRepository.save(it) },
//                        Contribution(
//                            musicianRepository.findByFirstNameAndLastName(
//                                "Bob",
//                                "Pasquarello"
//                            )!!, instrumentRepository.findByName("piano")!!
//                        ).also { contributionRepository.save(it) },
//                        Contribution(
//                            musicianRepository.findByFirstNameAndLastName(
//                                "Bob",
//                                "Pasquarello"
//                            )!!, instrumentRepository.findByName("triangle")!!
//                        ).also { contributionRepository.save(it) },
//                        Contribution(
//                            musicianRepository.findByFirstNameAndLastName(
//                                "Bob",
//                                "Stein"
//                            )!!, instrumentRepository.findByName("accordion")!!
//                        ).also { contributionRepository.save(it) },
//                        Contribution(
//                            musicianRepository.findByFirstNameAndLastName(
//                                "Bob",
//                                "Stein"
//                            )!!, instrumentRepository.findByName("synthesizer")!!
//                        ).also { contributionRepository.save(it) },
//                    ),
//                    samples = setOfNotNull(Sample("sample.mp3").also { sampleRepository.save(it) }),
//                    comments = setOfNotNull(Comment(
//                        "Bob Pasquarello", """
//                        In the early 1980’s, there was a lot of energy in the Philadelphia / Princeton area around contra dancing and traditional music. There were weekly Thursday and First Saturday night dances in Germantown, PA; weekly Wednesday and second Saturday night dances in Princeton and a weekly Friday Night dance in Lambertville, NJ. Bob Pasquarello and Bob Stein were already playing buddies with John Krumm in Col. Mike’s Dance Band and Barbara Greenberg was playing with Hold The Mustard and Rum and Onions.
//     Bob Stein and Barbara first met when Bob Pasquarello asked them to join him to provide background music at a folk fair. Shortly after that, Bob P was asked to provide a band for a Saturday night dance at Summit Church in Mt. Airy, PA, so he asked Bob and Barbara. Thinking it was a “one and done” opportunity, they played under the name A Flash In The Pan. They had so much fun together, they decided to keep on playing together!
//     The band name came from several places. When Bob P first played at a Wednesday Night dance in Princeton, Bob Dupre introduced him to a group of folks where every person in the group had a variation on the name Bob. It was a popular name!
//      A regular columnist for The Philadelphia Bulletin, Clark DeLeon, wrote a story about the naming of the new baby gorilla at the Philadelphia Zoo, who had been named Fred. Clark then got a letter from someone complaining that Fred was a terrible name for a poor defenseless baby gorilla, going on at some length about it. In the last paragraph, the writer stated. “I think they should name it Bob.” He listed lots of reasons and signed the letter, “Sincerely Yours, Bob Johnston, President, Committee to Name Everything Bob.” Bob P called up Barbara and Bob S right away to announce that he found the name for their band.
//     A Band Named Bob played for dances and weekends from Maine to Georgia and West to California. Dawn dances, dance camps, and festivals such as NEFFA, The Flurry and the Ann Arbor Dance Weekend filled the band’s schedule too. They recorded their CD/Cassette in 1994.
//     A Band Named Bob had a few variations, when Dan Beerbohm subbed for Barbara, we were A Dan Named Bob. There was a Lambertville dance where Stein and I dressed in drag and we were A Band Named Barbara. When Bob Mills played with Stein & I we were the NeanderBobs. (we also played as The Three Amigos)
//     One 3rd Saturday Contra in Mt. Airy PA featured Bob Dalsemer and a band made up of guys named Robert. (Bob Stein, Robert Mills, Robert LaRue, myself and a few others) We called the band A Roomful of Robert. Kathy and I made up name tags that all said Robert. I handed one to the first guy who entered and he looked at me funny and said “How did you know my name?”
//                    """.trimIndent()
//                    ).also { commentRepository.save(it) }),
//                    images = setOfNotNull(),
//                    lpRelease = false,
//                    cassetteRelease = true,
//                    cdRelease = true,
//                    city = "Philadelphia",
//                    state = "PA",
//                    country = "USA",
//                    location = "Philadelphia, PA",
//                    latitude = 40.045803f,
//                    longitude = -75.19441f,
//                )
//            ) {
//                recordingRepository.save(this)
//            }
//        }
//    }
}

fun main(args: Array<String>) {
    runApplication<MapApplication>(*args)
}
