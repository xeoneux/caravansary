package com.xeoneux.caravansary.converter

import com.xeoneux.caravansary.entity.ReservationEntity
import com.xeoneux.caravansary.request.ReservationRequest
import org.springframework.core.convert.converter.Converter

class ReservationRequestToReservationEntityConverter : Converter<ReservationRequest, ReservationEntity> {
    override fun convert(source: ReservationRequest): ReservationEntity {
        val reservationEntity = ReservationEntity()
        reservationEntity.checkin = source.checkin
        reservationEntity.checkout = source.checkout

        if (source.id != null) reservationEntity.id = source.id

        return reservationEntity
    }
}
