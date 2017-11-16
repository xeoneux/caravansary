package com.xeoneux.caravansary.converter;

import com.xeoneux.caravansary.entity.ReservationEntity;
import com.xeoneux.caravansary.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationEntityToReservationResponseConverter
        implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity source) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        return reservationResponse;
    }
}