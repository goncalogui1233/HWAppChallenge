package com.goncalo.myapplication.domain.use_case.rates

import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Result
import com.goncalo.myapplication.common.extensions.formatDecimalDigits
import com.goncalo.myapplication.domain.model.rates.PriceRates
import com.goncalo.myapplication.domain.repository.IRatesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val repository: IRatesRepository
) {
    private val ratesToUse = listOf("EUR", "USD", "GBP")

    operator fun invoke(basePrice: Double): Single<Result<List<Pair<String, String>>>> {
        return repository.getRates().map {
            filterRateList(it, basePrice)
        }.onErrorReturn {
            Result(
                isSuccess = false,
                errorMessage = R.string.rate_use_case_error
            )
        }
    }

    private fun filterRateList(priceRates: PriceRates, basePrice: Double) : Result<List<Pair<String, String>>> {
        val filteredList = priceRates.priceRates.filter { it.key in ratesToUse }
        val convertedPriceList = arrayListOf<Pair<String, String>>()


        return if(filteredList.isNotEmpty()) {
            filteredList.forEach {
                val convertedPrice = basePrice.times(it.value)
                convertedPriceList.add(Pair(it.key, convertedPrice.formatDecimalDigits(2)))
            }
             Result<List<Pair<String, String>>>(
                isSuccess = true,
                content = convertedPriceList
            )
        } else {
             Result(
                isSuccess = false,
                errorMessage = R.string.rate_use_case_error
            )
        }




    }

}