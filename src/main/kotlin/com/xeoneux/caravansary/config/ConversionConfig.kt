package com.xeoneux.caravansary.config

import com.xeoneux.caravansary.converter.ReservationEntityToReservationResponseConverter
import com.xeoneux.caravansary.converter.ReservationRequestToReservationEntityConverter
import com.xeoneux.caravansary.converter.RoomEntityToReservableRoomResponseConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ConversionServiceFactoryBean
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter

import java.util.HashSet

@Configuration
class ConversionConfig {
    private fun getConverters(): Set<Converter<*, *>> {
        val converters = HashSet<Converter<*, *>>()
        converters.add(RoomEntityToReservableRoomResponseConverter())
        converters.add(ReservationRequestToReservationEntityConverter())
        converters.add(ReservationEntityToReservationResponseConverter())

        return converters
    }

    @Bean
    fun conversionService(): ConversionService {
        val bean = ConversionServiceFactoryBean()
        bean.setConverters(getConverters())
        bean.afterPropertiesSet()

        return bean.`object`
    }
}
