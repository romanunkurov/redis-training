package ru.unkurov.pet.redistraining.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.unkurov.pet.redistraining.entity.Person
import ru.unkurov.pet.redistraining.service.PersonService

@RestController
@RequestMapping("/crud")
class CrudController @Autowired constructor(
    private val personService: PersonService
) {

    @PostMapping("/add", produces =  [MediaType.APPLICATION_JSON_VALUE])
    fun add(@RequestBody person: Person): ResponseEntity<Unit> {
        return ResponseEntity.ok(personService.savePerson(person))
    }

    @GetMapping("/count", produces =  [MediaType.APPLICATION_JSON_VALUE])
    fun count(): ResponseEntity<Long> {
        return ResponseEntity.ok(personService.numberOfPersons())
    }

    @GetMapping("/find/{id}", produces =  [MediaType.APPLICATION_JSON_VALUE])
    fun find( @PathVariable id: String): ResponseEntity<Person> {
        return ResponseEntity.ok(personService.findById(id))
    }

    @GetMapping("/delete", produces =  [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@RequestBody person: Person): ResponseEntity<Unit> {
        return ResponseEntity.ok(personService.delete(person))
    }
}