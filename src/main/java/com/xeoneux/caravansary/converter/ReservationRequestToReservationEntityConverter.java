package com.xeoneux.caravansary.converter;

import com.xeoneux.caravansary.entity.ReservationEntity;
import com.xeoneux.caravansary.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter
        implements Converter<ReservationRequest, ReservationEntity> {
    @Override
    public ReservationEntity convert(ReservationRequest source) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());

        if (source.getId() != null) reservationEntity.setId(source.getId());

        return reservationEntity;
    }
}
