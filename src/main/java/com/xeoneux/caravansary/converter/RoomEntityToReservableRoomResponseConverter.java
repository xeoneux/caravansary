package com.xeoneux.caravansary.converter;

import com.xeoneux.caravansary.entity.RoomEntity;
import com.xeoneux.caravansary.model.Links;
import com.xeoneux.caravansary.model.Self;
import com.xeoneux.caravansary.response.ReservableRoomResponse;
import com.xeoneux.caravansary.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservableRoomResponseConverter
        implements Converter<RoomEntity, ReservableRoomResponse> {
    @Override
    public ReservableRoomResponse convert(RoomEntity source) {
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());

        Links links = new Links();
        links.setSelf(self);

        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setId(source.getId());
        reservableRoomResponse.setRoomNumber(source.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(source.getPrice()));
        reservableRoomResponse.setLinks(links);

        return reservableRoomResponse;
    }
}
