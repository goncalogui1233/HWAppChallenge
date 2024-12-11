package com.goncalo.myapplication.domain.use_case.rates

import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Result
import com.goncalo.myapplication.domain.model.rates.PriceRates
import com.goncalo.myapplication.domain.repository.IRatesRepository
import com.goncalo.myapplication.domain.use_case.property.BasePropertyUseCase
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val repository: IRatesRepository
) : BasePropertyUseCase<Double, PriceRates, Result<HashMap<String, String>>>() {
    private val ratesToUse = listOf("EUR", "USD", "GBP")

    override suspend fun getContent() = repository.getRates()

    override fun handleException(obj: Exception): Result<HashMap<String, String>> {
        return Result(
            isSuccess = false,
            errorMessage = R.string.rate_use_case_error
        )
    }

    /**
     * This method gets the rates returned by the endpoints and it returns the
     * ones set in the array "rates to use".
     * The method calculates the correct price to show on screen
     */
    override fun handleSuccess(
        content: PriceRates,
        requestDuration: Long,
        successParams: Double?
    ): Result<HashMap<String, String>> {

        val filteredList = content.priceRates.filter { it.key in ratesToUse }
        val map = hashMapOf<String, String>()

        filteredList.forEach {
            map[it.key] = successParams?.times(it.value).toString()
        }

        return Result(
            isSuccess = true,
            content = map,
            requestDuration = requestDuration
        )
    }
}