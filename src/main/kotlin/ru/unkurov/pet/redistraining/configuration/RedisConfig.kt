package ru.unkurov.pet.redistraining.configuration

import io.lettuce.core.ReadFrom
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericToStringSerializer
import ru.unkurov.pet.redistraining.service.MessagePublisher
import ru.unkurov.pet.redistraining.service.MessagePublisherImpl
import ru.unkurov.pet.redistraining.service.MessageSubscriber


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val clientConfig = LettuceClientConfiguration.builder()
            .readFrom(ReadFrom.REPLICA_PREFERRED)
            .build()

        val serverConfig = RedisStandaloneConfiguration("127.0.0.1", 6379)
        return LettuceConnectionFactory(serverConfig, clientConfig)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(redisConnectionFactory())
            valueSerializer = GenericToStringSerializer(Any::class.java)
        }
    }


    @Bean
    fun messageListener(): MessageListenerAdapter {
        return MessageListenerAdapter(MessageSubscriber())
    }

    @Bean
    fun redisContainer(): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            setConnectionFactory(redisConnectionFactory())
            addMessageListener(messageListener(), topic())
        }
    }

    @Bean
    fun redisPublisher(): MessagePublisher {
        return MessagePublisherImpl(redisTemplate(), topic())
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("pubsub:movie")
    }
}