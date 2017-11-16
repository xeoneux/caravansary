package com.xeoneux.caravansary.repository;

import com.xeoneux.caravansary.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    RoomEntity findById(Long id);
}
