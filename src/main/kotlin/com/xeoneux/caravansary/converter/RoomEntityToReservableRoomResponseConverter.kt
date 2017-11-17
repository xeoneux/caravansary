package com.xeoneux.caravansary.converter

import com.xeoneux.caravansary.entity.RoomEntity
import com.xeoneux.caravansary.model.Links
import com.xeoneux.caravansary.model.Self
import com.xeoneux.caravansary.response.ReservableRoomResponse
import com.xeoneux.caravansary.rest.ResourceConstants
import org.springframework.core.convert.converter.Converter

class RoomEntityToReservableRoomResponseConverter : Converter<RoomEntity, ReservableRoomResponse> {
    override fun convert(source: RoomEntity): ReservableRoomResponse {
        val self = Self()
        self.ref = ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.id

        val links = Links()
        links.self = self

        val reservableRoomResponse = ReservableRoomResponse()
        reservableRoomResponse.id = source.id
        reservableRoomResponse.roomNumber = source.roomNumber
        reservableRoomResponse.price = Integer.valueOf(source.price!!)
        reservableRoomResponse.links = links

        return reservableRoomResponse
    }
}
