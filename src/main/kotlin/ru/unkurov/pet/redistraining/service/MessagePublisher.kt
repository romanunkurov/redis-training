package ru.unkurov.pet.redistraining.service

interface MessagePublisher {
    fun publish(message: String)
}