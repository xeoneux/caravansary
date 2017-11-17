package com.xeoneux.caravansary.repository

import com.xeoneux.caravansary.entity.RoomEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : CrudRepository<RoomEntity, Long> {
    fun findById(id: Long?): RoomEntity
}
