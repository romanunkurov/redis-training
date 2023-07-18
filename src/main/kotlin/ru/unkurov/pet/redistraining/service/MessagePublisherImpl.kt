package ru.unkurov.pet.redistraining.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic

class MessagePublisherImpl @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val topic: ChannelTopic) : MessagePublisher {

    override fun publish(message: String) {
        redisTemplate.convertAndSend(topic.topic, message);
    }
}