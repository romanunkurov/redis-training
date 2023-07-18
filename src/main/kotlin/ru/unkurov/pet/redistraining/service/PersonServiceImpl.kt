package ru.unkurov.pet.redistraining.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.unkurov.pet.redistraining.entity.Person
import ru.unkurov.pet.redistraining.repository.PersonRepository

@Service
class PersonServiceImpl @Autowired constructor(
    private val personRepository: PersonRepository
) : PersonService {

    override fun savePerson(person: Person) {
        val person = personRepository.save(person)
        println("Person $person saved with key: ${person.id}")
    }

    override fun findById(id: String): Person {
        val person = personRepository.findById(id).get()
        println("Person found: $person")
       return person
    }

    override fun numberOfPersons(): Long {
        val count = personRepository.count()
        println("Number of persons retrieved: $count")
        return count
    }

    override fun delete(person: Person) {
        personRepository.delete(person)
        println("Successfully deleted person with ID ${person.id}")
    }
}