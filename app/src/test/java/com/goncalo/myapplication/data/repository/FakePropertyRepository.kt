package com.goncalo.myapplication.data.repository

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository

class FakePropertyRepository : IPropertyRepository {

    val properties = Properties(arrayListOf())

    override suspend fun getPropertiesList(): Properties {
        return properties
    }

    fun addProperty(property: Property) {
        properties.listProperties.add(property)
    }
}