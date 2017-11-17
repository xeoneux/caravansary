package com.xeoneux.caravansary.converter

import com.xeoneux.caravansary.entity.ReservationEntity
import com.xeoneux.caravansary.response.ReservationResponse
import org.springframework.core.convert.converter.Converter

class ReservationEntityToReservationResponseConverter : Converter<ReservationEntity, ReservationResponse> {
    override fun convert(source: ReservationEntity): ReservationResponse {
        val reservationResponse = ReservationResponse()
        reservationResponse.checkin = source.checkin
        reservationResponse.checkout = source.checkout

        return reservationResponse
    }
}
