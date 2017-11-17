package com.xeoneux.caravansary.repository

import com.xeoneux.caravansary.entity.ReservationEntity
import org.springframework.data.repository.CrudRepository

interface ReservationRepository : CrudRepository<ReservationEntity, Long>
