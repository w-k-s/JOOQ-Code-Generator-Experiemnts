package io.wks.mavenflyway.data.access

import io.wks.mavenflyway.core.person.Person
import io.wks.mavenflyway.core.person.PersonId
import io.wks.mavenflyway.core.person.People
import io.wks.mavenflyway.jooq.jooq_experiment.Tables
import io.wks.mavenflyway.jooq.jooq_experiment.tables.Person.PERSON
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class PersonDao(private val dslContext: DSLContext) {

    fun insert(person: Person): Person {
        val id = dslContext.insertInto(Tables.PERSON, PERSON.FIRST_NAME, PERSON.LAST_NAME)
            .values(person.firstName, person.lastName)
            .returningResult(PERSON.ID)
            .fetchOne()
            ?.value1() ?: throw RuntimeException("Failed to retrieve id of created user")
        return person.copy(id = PersonId(id))
    }

    fun list(): People {
        return People(dslContext.selectFrom(Tables.PERSON)
            .fetch()
            .map {
                Person(
                    id = PersonId(it.id),
                    firstName = it.firstName,
                    lastName = it.lastName
                )
            }.toList()
        )
    }
}