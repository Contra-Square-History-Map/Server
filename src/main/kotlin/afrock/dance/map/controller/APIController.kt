package afrock.dance.map.controller

import afrock.dance.map.API_BASE_ROUTE
import afrock.dance.map.INSTRUMENTS
import afrock.dance.map.JSON_BASE_ROUTE
import afrock.dance.map.PROTO_BASE_ROUE
import afrock.dance.map.RECORDING
import afrock.dance.map.RECORDINGS
import afrock.dance.map.UPLOAD
import afrock.dance.map.entity.Comment
import afrock.dance.map.entity.Contribution
import afrock.dance.map.entity.Image
import afrock.dance.map.entity.Instrument
import afrock.dance.map.entity.Musician
import afrock.dance.map.entity.Recording
import afrock.dance.map.entity.Sample
import afrock.dance.map.repository.InstrumentRepository
import afrock.dance.map.repository.MusicianRepository
import afrock.dance.map.repository.RecordingRepository
import com.google.protobuf.util.JsonFormat
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.Date
import afrock.dance.map.InstrumentList as InstrumentsMessage
import afrock.dance.map.Recording as RecordingMessage
import afrock.dance.map.RecordingList as RecordingListMessage
import afrock.dance.map.instrumentList as instrumentsMessage
import afrock.dance.map.recordingList as recordingListMessage

@RestController
@RequestMapping(API_BASE_ROUTE)
@Cacheable("jsonCache")
class APIController(
    val recordingRepository: RecordingRepository,
    val instrumentRepository: InstrumentRepository,
    val musicianRepository: MusicianRepository,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun getAllRecordings(): RecordingListMessage = recordingListMessage {
        recordings.addAll(recordingRepository.findAll().map { recording -> recording.toProto() })
    }

    fun queryRecordings(query: String): RecordingListMessage = recordingListMessage {
        recordings.addAll(recordingRepository.search(query).map { recording -> recording.toProto() })
    }

    @GetMapping(JSON_BASE_ROUTE + RECORDINGS)
    fun getRecordingsJson(@RequestParam(name = "q", required = false) query: String?): String {
        val recordings = if (query == null || query.length < 2) {
            getAllRecordings()
        } else {
            queryRecordings(query)
        }
        return JsonFormat.printer().omittingInsignificantWhitespace().print(recordings)
    }

    @GetMapping(PROTO_BASE_ROUE + RECORDINGS)
    fun getRecordingsProto(@RequestParam(name = "q", required = false) query: String?): ByteArray {
        val recordings = if (query == null || query.length < 2) {
            getAllRecordings()
        } else {
            queryRecordings(query)
        }
        return recordings.toByteArray()
    }

    fun getRecording(id: String): RecordingMessage {
        try {
            return recordingRepository.findByIdOrNull(id)?.toProto() ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "A comment with the requested ID could not be found."
            )
        } catch (e: NumberFormatException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Parameter 'id' could not be resolved to a long value."
            )
        }
    }

    @GetMapping(JSON_BASE_ROUTE + RECORDING)
    fun getRecordingJson(@RequestParam(name = "id", required = true) id: String): String {
        return JsonFormat.printer().omittingInsignificantWhitespace().print(getRecording(id))
    }

    @GetMapping(PROTO_BASE_ROUE + RECORDING)
    fun getRecordingProto(@RequestParam(name = "id", required = true) id: String): ByteArray {
        return getRecording(id).toByteArray()
    }

    fun getInstruments(): InstrumentsMessage {
        val allInst = instrumentRepository.findAll()
        return instrumentsMessage {
            instruments.addAll(allInst.map {
                it.name
            })
        }
    }

    @GetMapping(JSON_BASE_ROUTE + INSTRUMENTS)
    fun getInstrumentsJson(): String {
        return JsonFormat.printer().omittingInsignificantWhitespace().print(getInstruments())
    }

    @GetMapping(PROTO_BASE_ROUE + INSTRUMENTS)
    fun getInstrumentsProto(): ByteArray {
        return getInstruments().toByteArray()
    }

    @PostMapping(PROTO_BASE_ROUE + UPLOAD)
    fun uploadNewRecording(@RequestBody data: ByteArray): ResponseEntity<Any> {
        val message = RecordingMessage.parseFrom(data)

        log.debug("Received upload request for ${message.band}")

        recordingRepository.save(
            Recording(
                title = message.title,
                band = message.band,
                cassetteRelease = message.cassetteRelease,
                cdRelease = message.cdRelease,
                lpRelease = message.lpRelease,
                latitude = message.latitude,
                longitude = message.longitude,
                city = message.location,
                country = message.location,
                state = message.location,
                location = message.location,
                releaseDate = Date.from(Instant.ofEpochSecond(message.releaseTime)),
                comments = message.commentsList.map { commentMessage ->
                    Comment(commentMessage.author, commentMessage.text)
                }.toSet(),
                samples = message.samplesList.map { sampleMessage ->
                    Sample(title = sampleMessage.title, path = sampleMessage.url)
                }.toSet(),
                images = message.imagesList.map { imageMessage ->
                    return@map Image(imageMessage)
                }.toSet(),
                contributions = message.contributionsList.map { contributionMessage ->
                    val musician = musicianRepository.findByFirstNameAndLastName(
                        contributionMessage.musician.firstName, contributionMessage.musician.lastName
                    ) ?: musicianRepository.save(
                        Musician(
                            contributionMessage.musician.firstName, contributionMessage.musician.lastName
                        )
                    )

                    val instrument =
                        instrumentRepository.findByName(contributionMessage.instrument) ?: instrumentRepository.save(
                            Instrument(contributionMessage.instrument)
                        )

                    return@map Contribution(musician, instrument)

                }.toSet(),
            )
        )

        return ResponseEntity.ok().build()
    }
}