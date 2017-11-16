package com.xeoneux.caravansary.rest;

import com.xeoneux.caravansary.converter.RoomEntityToReservableRoomResponseConverter;
import com.xeoneux.caravansary.entity.ReservationEntity;
import com.xeoneux.caravansary.entity.RoomEntity;
import com.xeoneux.caravansary.repository.PageableRoomRepository;
import com.xeoneux.caravansary.repository.ReservationRepository;
import com.xeoneux.caravansary.repository.RoomRepository;
import com.xeoneux.caravansary.request.ReservationRequest;
import com.xeoneux.caravansary.response.ReservableRoomResponse;
import com.xeoneux.caravansary.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired ReservationRepository reservationRepository;

    @Autowired ConversionService conversionService;

    @RequestMapping(
        path = "",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout,
            Pageable pageable) {

        Page<RoomEntity> roomEntityPage = pageableRoomRepository.findAll(pageable);

        return roomEntityPage.map(new RoomEntityToReservableRoomResponseConverter());
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

        ReservationEntity reservationEntity =
                conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);

        RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId());
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);
        reservationEntity.setRoomEntity(roomEntity);

        ReservationResponse reservationResponse =
                conversionService.convert(reservationEntity, ReservationResponse.class);

        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @RequestMapping(
        path = "",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<ReservableRoomResponse> updateReservation(
            @RequestBody ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
