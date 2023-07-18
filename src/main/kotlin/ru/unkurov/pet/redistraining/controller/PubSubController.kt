package ru.unkurov.pet.redistraining.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.unkurov.pet.redistraining.entity.Movie
import ru.unkurov.pet.redistraining.repository.RedisPubSubRepository

@RestController
@RequestMapping("/pubsub")
class PubSubController @Autowired constructor(
    private val redisPubSubRepository: RedisPubSubRepository
) {

    @GetMapping("/add", produces =  [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(@RequestParam key: String,
               @RequestParam value: String): ResponseEntity<Unit> {
        val movie = Movie(key, value)
        return ResponseEntity.ok(redisPubSubRepository.add(movie))
    }
}