package ru.unkurov.pet.redistraining.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash



@RedisHash("people")
data class Person(
    @Id
    var id: String? = null,
    var firstname: String,
    var lastname: String,
)