package com.goncalo.myapplication.domain.use_case.property

import com.goncalo.myapplication.data.repository.FakePropertyRepository
import com.goncalo.myapplication.domain.model.property.Property
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPropertyUseCaseTest {
    private lateinit var useCase: GetPropertyUseCase
    private lateinit var fakeRepository: FakePropertyRepository

    @Before
    fun setUp() {
        fakeRepository = FakePropertyRepository()
        useCase = GetPropertyUseCase(fakeRepository)

        ('A'..'Z').forEachIndexed { index, i ->
            fakeRepository.addProperty(
                Property(
                    propertyId = index,
                    propertyName = i.toString(),
                    isPropertyFeatured = false,
                    addressTwo = "",
                    addressOne = "",
                    description = ""
                )
            )
        }
    }

    @Test
    fun checkProprtyReturnedMatchesId() = runBlocking {
        val id = 5
        val prop = useCase(id)
        assertEquals(prop.content?.propertyId, id)
    }
}