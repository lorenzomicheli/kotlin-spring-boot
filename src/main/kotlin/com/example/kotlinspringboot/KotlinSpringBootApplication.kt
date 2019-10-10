package com.example.kotlinspringboot

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class KotlinSpringBootApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringBootApplication>(*args)
}


@Configuration
class DatabaseConfiguration {
	@Bean
	fun databaseInitializer(personRepository: PersonRepository) = ApplicationRunner {
		personRepository.save(Person("Alice"))
		personRepository.save(Person("Bob"))
	}
}
