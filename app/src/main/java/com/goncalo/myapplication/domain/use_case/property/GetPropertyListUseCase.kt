package com.goncalo.myapplication.domain.use_case.property

import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Result
import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import javax.inject.Inject

class GetPropertyListUseCase @Inject constructor(
    private val repository: IPropertyRepository
) : BasePropertyUseCase<Any, Properties, Result<List<Property>>>() {

    override suspend fun getContent() = repository.getPropertiesList()

    override fun handleSuccess(
        content: Properties,
        requestDuration: Long,
        successParams: Any?
    ): Result<List<Property>> {
        return Result(
            isSuccess = true,
            content = content.listProperties,
            requestDuration = requestDuration
        )
    }

    override fun handleException(obj: Exception): Result<List<Property>> {
        return Result(isSuccess = false, errorMessage = R.string.property_list_use_case_error)
    }

}