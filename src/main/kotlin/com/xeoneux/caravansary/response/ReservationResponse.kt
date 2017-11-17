package com.xeoneux.caravansary.response

import java.time.LocalDate

class ReservationResponse {

    var id: Long? = null
    var checkin: LocalDate? = null
    var checkout: LocalDate? = null

    constructor() : super() {}

    constructor(id: Long?, checkin: LocalDate, checkout: LocalDate) : super() {
        this.id = id
        this.checkin = checkin
        this.checkout = checkout
    }
}
