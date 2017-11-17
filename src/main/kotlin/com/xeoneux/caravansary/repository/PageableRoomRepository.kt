package com.xeoneux.caravansary.repository

import com.xeoneux.caravansary.entity.RoomEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface PageableRoomRepository : PagingAndSortingRepository<RoomEntity, Long> {
    fun findById(id: Long?, page: Pageable): Page<RoomEntity>
}
