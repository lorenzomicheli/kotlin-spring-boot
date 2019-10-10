package com.example.kotlinspringboot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.kafka.test.context.EmbeddedKafka


@EmbeddedKafka(topics = ["foo"])
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PersonControllerTest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun findAllPerson() {
        val response = restTemplate!!.exchange<List<Person>>("http://localhost:$port/people",
                HttpMethod.GET, null, object : ParameterizedTypeReference<List<Person>>() {})
        assertEquals(2, response.body!!.size)
    }

    @Test
    fun findPerson() {
        val person = restTemplate!!.getForObject<Person>("http://localhost:$port/people/1", Person::class)
        assertNotNull(person)
    }
}