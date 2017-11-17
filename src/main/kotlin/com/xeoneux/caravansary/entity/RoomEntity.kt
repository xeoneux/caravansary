package com.xeoneux.caravansary.entity

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.util.ArrayList

@Entity
@Table(name = "Room")
data class RoomEntity(@Id
                      @GeneratedValue(strategy = GenerationType.AUTO)
                      var id: Long? = null,

                      @NotNull
                      var roomNumber: Int? = null,

                      @NotNull
                      var price: String? = null,

                      @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
                      private var reservationEntityList: MutableList<ReservationEntity>? = null) {

    constructor(roomNumber: Int, price: String) : this(null, roomNumber, price)

    fun addReservationEntity(reservationEntity: ReservationEntity) {
        if (reservationEntityList == null) reservationEntityList = ArrayList()

        reservationEntityList!!.add(reservationEntity)
    }
}
