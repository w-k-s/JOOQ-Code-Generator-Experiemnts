package io.wks.mavenflyway.core.person

data class PersonId(val value: Long)
data class Person(val id: PersonId, val firstName: String, val lastName: String)

class People(private val people: Collection<Person>) : Collection<Person> {
    override val size = people.size
    override fun contains(element: Person) = people.contains(element)
    override fun containsAll(elements: Collection<Person>) = people.containsAll(elements)
    override fun isEmpty() = people.isEmpty()
    override fun iterator() = people.iterator()
}