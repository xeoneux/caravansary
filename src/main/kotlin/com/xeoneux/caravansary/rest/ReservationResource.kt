package com.xeoneux.caravansary.rest

import com.xeoneux.caravansary.converter.RoomEntityToReservableRoomResponseConverter
import com.xeoneux.caravansary.entity.ReservationEntity
import com.xeoneux.caravansary.entity.RoomEntity
import com.xeoneux.caravansary.repository.PageableRoomRepository
import com.xeoneux.caravansary.repository.ReservationRepository
import com.xeoneux.caravansary.repository.RoomRepository
import com.xeoneux.caravansary.request.ReservationRequest
import com.xeoneux.caravansary.response.ReservableRoomResponse
import com.xeoneux.caravansary.response.ReservationResponse
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
class ReservationResource(private val roomRepository: RoomRepository,
                          private val conversionService: ConversionService,
                          private val reservationRepository: ReservationRepository,
                          private val pageableRoomRepository: PageableRoomRepository) {

    @GetMapping("",
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getAvailableRooms(@RequestParam(value = "checkin")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          checkin: LocalDate,
                          @RequestParam(value = "checkout")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          checkout: LocalDate,
                          pageable: Pageable) = pageableRoomRepository
            .findAll(pageable)
            .map(RoomEntityToReservableRoomResponseConverter())

    @GetMapping("/{roomId}",
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun getAvailableRooms(@PathVariable
                          roomId: Long) =
            ResponseEntity<RoomEntity>(roomRepository.findById(roomId), HttpStatus.OK)

    @PostMapping("",
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun createReservation(@RequestBody
                          reservationRequest: ReservationRequest): ResponseEntity<ReservationResponse> {
        val reservationEntity = conversionService.convert(reservationRequest, ReservationEntity::class.java)
        reservationRepository.save<ReservationEntity>(reservationEntity)

        val roomEntity = roomRepository.findById(reservationRequest.roomId)
        roomEntity.addReservationEntity(reservationEntity)
        roomRepository.save(roomEntity)
        reservationEntity.roomEntity = roomEntity

        val reservationResponse = conversionService.convert(reservationEntity, ReservationResponse::class.java)

        return ResponseEntity(reservationResponse, HttpStatus.CREATED)
    }

    @PutMapping("",
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun updateReservation(@RequestBody
                          reservationRequest: ReservationRequest) =
            ResponseEntity<ReservableRoomResponse>(ReservableRoomResponse(), HttpStatus.OK)

    @DeleteMapping("")
    fun deleteReservation(@PathVariable
                          reservationId: Long) = ResponseEntity<Void>(HttpStatus.NO_CONTENT)
}
