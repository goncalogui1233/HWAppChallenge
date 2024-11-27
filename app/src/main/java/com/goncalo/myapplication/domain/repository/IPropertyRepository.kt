package com.goncalo.myapplication.domain.repository

import com.goncalo.myapplication.domain.model.property.Properties

interface IPropertyRepository {

    suspend fun getPropertiesList(): Properties?

}