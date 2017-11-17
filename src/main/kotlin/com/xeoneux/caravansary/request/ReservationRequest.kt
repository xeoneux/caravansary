package com.xeoneux.caravansary.request

import org.springframework.format.annotation.DateTimeFormat

import java.time.LocalDate

class ReservationRequest {

    var id: Long? = null
    var roomId: Long? = null

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var checkin: LocalDate? = null

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var checkout: LocalDate? = null

    constructor() : super() {}

    constructor(roomId: Long?, checkin: LocalDate, checkout: LocalDate) {
        this.roomId = roomId
        this.checkin = checkin
        this.checkout = checkout
    }
}
