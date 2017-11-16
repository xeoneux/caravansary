package com.xeoneux.caravansary.rest;

import com.xeoneux.caravansary.converter.RoomEntityToReservationResponseConverter;
import com.xeoneux.caravansary.entity.RoomEntity;
import com.xeoneux.caravansary.repository.PageableRoomRepository;
import com.xeoneux.caravansary.repository.RoomRepository;
import com.xeoneux.caravansary.request.ReservationRequest;
import com.xeoneux.caravansary.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
public class ReservationResource {

    @Autowired RoomRepository roomRepository;
    @Autowired PageableRoomRepository pageableRoomRepository;

    @RequestMapping(
        path = "",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Page<ReservationResponse> getAvailableRooms(
            @RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout,
            Pageable pageable) {

        Page<RoomEntity> roomEntityPage = pageableRoomRepository.findAll(pageable);

        return roomEntityPage.map(new RoomEntityToReservationResponseConverter());
    }

    @RequestMapping(
        path = "/{roomId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<RoomEntity> getAvailableRooms(@PathVariable Long roomId) {
        RoomEntity roomEntity = roomRepository.findById(roomId);
        return new ResponseEntity<>(roomEntity, HttpStatus.OK);
    }

    @RequestMapping(
        path = "",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservationResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(
        path = "",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ReservationResponse> updateReservation(
            @RequestBody ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservationResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
