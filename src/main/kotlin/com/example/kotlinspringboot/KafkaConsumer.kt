package com.example.kotlinspringboot

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import net.logstash.logback.argument.StructuredArguments.kv
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

internal const val RECEIVED_MESSAGE_METRIC = "received.message"

@Service
class KafkaConsumer(private val personRepository: PersonRepository, private val registry: MeterRegistry) {

    private final val counter: Counter = registry.counter(RECEIVED_MESSAGE_METRIC)

    @KafkaListener(topics = ["\${app.topic}"])
    fun receive(payload: String) {
        LOGGER.info("Received {}", kv("payload", payload))
        personRepository.save(Person(payload))
        counter.increment()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }
}