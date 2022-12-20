package com.test

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class BugTest {

    @Inject
    lateinit var repository: ParentRepository

    @Test
    fun test() {
        val id = repository.save(Parent("a", "b", mutableMapOf("child" to Child("nowhere")))).id
        val record = repository.findById(id).get()
        assertEquals(Child::class, record.children["child"]!!::class)
    }
}