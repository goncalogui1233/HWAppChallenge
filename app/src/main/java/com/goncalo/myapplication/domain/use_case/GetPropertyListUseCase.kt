package com.goncalo.myapplication.domain.use_case

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import retrofit2.Response
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repository: IPropertyRepository
) {
    suspend operator fun invoke(): Result<List<Property>> {

        return try {
            val response = repository.getPropertiesList()

            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleError(response)
            }
        } catch (e: Exception) {
            handleException(e.message.orEmpty())
        }
    }

    private fun handleSuccess(response: Response<Properties>) = Result(
        isSuccess = true,
        content = response.body()?.listProperties,
        requestDuration = (response.raw().receivedResponseAtMillis - response.raw().sentRequestAtMillis)
    )


    private fun handleError(response: Response<Properties>): Result<Nothing> =
        Result(isSuccess = false, errorMessage = response.message())

    private fun handleException(message: String): Result<Nothing> =
        Result(isSuccess = false, errorMessage = message)

}


data class Result<out T> (
    val isSuccess: Boolean,
    val content: T? = null,
    val requestDuration: Long = 0L,
    val errorMessage: String? = null
)