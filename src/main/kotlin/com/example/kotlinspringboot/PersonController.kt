package com.example.kotlinspringboot

import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.*

@Table(name = "person")
@Entity
class Person (val name: String,
              @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long? = null)

interface PersonRepository : CrudRepository<Person, Long> {}

@RestController
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping("/people")
    fun findAll(): MutableIterable<Person> = personRepository.findAll()

    @GetMapping("/people/{id}")
    fun getPerson(@PathVariable id: String) = personRepository.findById(id.toLong())
}