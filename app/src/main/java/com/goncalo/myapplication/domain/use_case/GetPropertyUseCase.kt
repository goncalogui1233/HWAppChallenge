package com.goncalo.myapplication.domain.use_case

import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import javax.inject.Inject

class GetPropertyUseCase @Inject constructor(
    private val repository: IPropertyRepository,
) {

    suspend operator fun invoke(propertyId: Int): Property? {
        /*return try {
            val response = repository.getPropertiesList()
            response?.let { r ->
                r.listProperties.firstOrNull { it.propertyId == propertyId }
            } ?: kotlin.run {
                null
            }
        } catch (e: Exception) {
            null
        }*/

        return null
    }

}



