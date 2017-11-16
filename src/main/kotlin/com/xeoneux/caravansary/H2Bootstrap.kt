package com.xeoneux.caravansary

import com.xeoneux.caravansary.entity.RoomEntity
import com.xeoneux.caravansary.repository.RoomRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class H2Bootstrap(private val roomRepository: RoomRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("Bootstrapping data: ")

        roomRepository.save(RoomEntity(405, "200"))
        roomRepository.save(RoomEntity(406, "220"))
        roomRepository.save(RoomEntity(407, "250"))

        val rooms = roomRepository.findAll()

        println("Printing out data: ")

        rooms.forEach { println(it.roomNumber) }
    }
}
