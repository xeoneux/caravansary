package com.xeoneux.caravansary.entity

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.time.LocalDate

@Entity
@Table(name = "Reservation")
data class ReservationEntity(@Id
                             @GeneratedValue(strategy = GenerationType.AUTO)
                             var id: Long? = null,

                             @NotNull
                             var checkin: LocalDate? = null,

                             @NotNull
                             var checkout: LocalDate? = null,

                             @ManyToOne
                             var roomEntity: RoomEntity? = null) {

    constructor(checkin: LocalDate, checkout: LocalDate) : this(null, checkin, checkout)
}
