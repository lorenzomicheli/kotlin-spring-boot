package com.example.kotlinspringboot

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@EmbeddedKafka(topics = ["foo"])
@DirtiesContext
class KotlinSpringBootApplicationTests {

	@Test
	fun contextLoads() {
	}

}
