package ru.unkurov.pet.redistraining.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.unkurov.pet.redistraining.entity.Person

@Repository
interface PersonRepository: CrudRepository<Person, String> {
}