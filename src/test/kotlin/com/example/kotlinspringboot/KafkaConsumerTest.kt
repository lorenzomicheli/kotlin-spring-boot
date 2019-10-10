package com.example.kotlinspringboot

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class KafkaConsumerTest {
    @Test
    fun test() {
        val meterRegistry = mockk<MeterRegistry>(relaxed = true)
        val personRepository = mockk<PersonRepository>(relaxed = true)
        val metricMock = spyk<Counter>()
        every { personRepository.save(any<Person>()) } returns Person("Carl")

        every { meterRegistry.counter(RECEIVED_MESSAGE_METRIC) } returns metricMock
        val kafkaConsumer = KafkaConsumer(personRepository, meterRegistry)
        kafkaConsumer.receive("Carl")

        verify { metricMock.increment() }

        verify { personRepository.save(any<Person>()) }
    }

}