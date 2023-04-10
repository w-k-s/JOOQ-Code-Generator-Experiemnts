package io.wks.mavenflyway.data.access

import io.wks.mavenflyway.core.person.Person
import io.wks.mavenflyway.core.person.PersonId
import io.wks.mavenflyway.jooq.jooq_experiment.Tables
import org.assertj.core.api.Assertions.assertThat
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PersonDaoIT : DaoIT() {

    @Autowired private lateinit var underTest: PersonDao
    @Autowired private lateinit var dslContext: DSLContext

    @AfterEach
    fun tearDown() {
        dslContext.deleteFrom(Tables.PERSON).execute()
    }

    @Test
    fun `GIVEN a person WHEN it is saved THEN it can be retrieved`() {
        // GIVEN
        val person = Person(id = PersonId(0), firstName = "Jack", lastName = "Torrance")

        // WHEN
        underTest.insert(person)

        // THEN
        val people = underTest.list()
        assertThat(people.first())
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(person)
    }
}