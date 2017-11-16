package com.xeoneux.caravansary.converter;

import com.xeoneux.caravansary.entity.RoomEntity;
import com.xeoneux.caravansary.model.Links;
import com.xeoneux.caravansary.model.Self;
import com.xeoneux.caravansary.response.ReservationResponse;
import com.xeoneux.caravansary.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservationResponseConverter
        implements Converter<RoomEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(RoomEntity source) {
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());

        Links links = new Links();
        links.setSelf(self);

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setRoomNumber(source.getRoomNumber());
        reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
        reservationResponse.setLinks(links);

        return reservationResponse;
    }
}
