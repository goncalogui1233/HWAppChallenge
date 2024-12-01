package com.goncalo.myapplication.domain.use_case.property

import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Result
import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import javax.inject.Inject

class GetPropertyUseCase @Inject constructor(
    private val repository: IPropertyRepository,
): BasePropertyUseCase<Int, Properties, Result<Property>>() {

    override suspend fun getContent() = repository.getPropertiesList()

    override fun handleException(obj: Exception): Result<Property> {
        return Result(
            isSuccess = false,
            errorMessage = R.string.rate_use_case_error
        )
    }

    override fun handleSuccess(
        content: Properties,
        requestDuration: Long,
        successParams: Int?
    ): Result<Property> {
        val filteredProperty =
            content.listProperties.firstOrNull { it.propertyId == successParams }
        filteredProperty?.let {
            return Result(
                isSuccess = true,
                content = it,
                requestDuration = requestDuration
            )
        } ?: run {
            return Result(
                isSuccess = false,
                errorMessage = R.string.rate_use_case_error
            )
        }
    }

}



