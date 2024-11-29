package com.goncalo.myapplication.domain.use_case

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import retrofit2.Response
import javax.inject.Inject

class GetPropertyUseCase @Inject constructor(
    private val repository: IPropertyRepository,
) {

    suspend operator fun invoke(propertyId: Int): Result<Property> {
        return try {
            val response = repository.getPropertiesList()

            return if (response.isSuccessful) {
                handleSuccess(response, propertyId)
            } else {
                handleError(response)
            }

        } catch (e: Exception) {
            handleException(e.message.orEmpty())
        }
    }

    private fun handleSuccess(response: Response<Properties>, propertyId: Int): Result<Property> {
        val filteredProperty =
            response.body()?.listProperties?.firstOrNull { it.propertyId == propertyId }
        filteredProperty?.let {
            return Result(
                isSuccess = true,
                content = it,
                requestDuration = response.raw().receivedResponseAtMillis - response.raw().sentRequestAtMillis
            )
        } ?: run {
            return Result(
                isSuccess = false,
                errorMessage = "Unable to get the item, try again later"
            )
        }
    }

    private fun handleError(response: Response<Properties>): Result<Nothing> {
        return Result(
            isSuccess = false,
            errorMessage = response.message()
        )
    }

    private fun handleException(eMessage: String): Result<Nothing> {
        return Result(
            isSuccess = false,
            errorMessage = eMessage
        )
    }

}



