package com.goncalo.myapplication.data.repository

import com.goncalo.myapplication.data.network.IHWAppChallengeApi
import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
     private val api: IHWAppChallengeApi
): IPropertyRepository {

    override suspend fun getPropertiesList(): Properties {
        return api.getPropertiesList()
    }
}