package ru.unkurov.pet.redistraining.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import ru.unkurov.pet.redistraining.entity.Movie
import ru.unkurov.pet.redistraining.service.MessagePublisher

@Repository
class RedisPubSubRepositoryImpl @Autowired constructor(
    private val template: RedisTemplate<String, String>,
    private val messagePublisher: MessagePublisher
) : RedisPubSubRepository {

    var hashOperations: HashOperations<String ,String, Any> = template.opsForHash()

    private val MOVIE_KEY = "pubsub:movie"

    override fun add(movie: Movie) {
        messagePublisher.publish(movie.toString())
        hashOperations.put(MOVIE_KEY, movie.id, movie.title)
    }

    override fun findAllMovies(): Map<String, Any?>? {
        return hashOperations.entries(MOVIE_KEY)
    }

    override fun delete(id: String) {
        hashOperations.delete(MOVIE_KEY, id)
    }

    override fun findMovie(id: String): Movie? {
        return hashOperations[MOVIE_KEY, id] as Movie?
    }
}