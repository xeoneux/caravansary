package com.xeoneux.caravansary.response

import com.xeoneux.caravansary.model.Links

class ReservableRoomResponse {

    var id: Long? = null
    var roomNumber: Int? = null
    var price: Int? = null
    var links: Links? = null

    constructor() : super() {}

    constructor(roomNumber: Int?, price: Int?) : super() {
        this.roomNumber = roomNumber
        this.price = price
    }
}
