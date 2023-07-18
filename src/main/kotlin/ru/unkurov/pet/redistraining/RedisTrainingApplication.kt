package ru.unkurov.pet.redistraining

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisTrainingApplication

fun main(args: Array<String>) {
    runApplication<RedisTrainingApplication>(*args)
}
