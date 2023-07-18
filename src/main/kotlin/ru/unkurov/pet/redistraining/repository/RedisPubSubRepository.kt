package ru.unkurov.pet.redistraining.repository

import ru.unkurov.pet.redistraining.entity.Movie




interface RedisPubSubRepository {

    fun findAllMovies(): Map<String, Any?>?
    fun add(movie: Movie)
    fun delete(id: String)
    fun findMovie(id: String): Movie?
}