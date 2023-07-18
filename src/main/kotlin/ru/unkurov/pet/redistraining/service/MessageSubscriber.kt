package ru.unkurov.pet.redistraining.service

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener

class MessageSubscriber: MessageListener {
    private var messageList = mutableListOf<String>()

    override fun onMessage(message: Message, pattern: ByteArray?) {
       messageList.add(message.toString())
       println("Message received: ${message.body}")
    }
}