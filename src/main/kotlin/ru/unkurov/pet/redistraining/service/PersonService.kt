package ru.unkurov.pet.redistraining.service

import ru.unkurov.pet.redistraining.entity.Person

interface PersonService {
    fun savePerson(person: Person)

    fun findById(id: String): Person

    fun numberOfPersons(): Long

    fun delete(person: Person)

}