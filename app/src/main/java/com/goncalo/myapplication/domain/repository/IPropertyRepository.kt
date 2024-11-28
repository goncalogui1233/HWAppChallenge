package com.goncalo.myapplication.domain.repository

import com.goncalo.myapplication.domain.model.property.Properties
import retrofit2.Response

interface IPropertyRepository {

    suspend fun getPropertiesList(): Response<Properties>

}