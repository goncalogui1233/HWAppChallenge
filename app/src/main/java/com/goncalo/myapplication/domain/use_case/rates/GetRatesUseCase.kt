package com.goncalo.myapplication.domain.use_case.rates

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
    private var rates: PriceRates? = null

    operator fun invoke(basePrice: Double): Single<Result<List<Pair<String, String>>>> {
        return if (rates != null) {
            Single.just(filterRateList(rates!!, basePrice))
        } else {
            repository.getRates().map {
                rates = it
                filterRateList(it, basePrice)
            }.onErrorReturn {
                Result(
                    isSuccess = false,
                    errorMessage = "Null"
                )
            }
        }
    }

    private fun filterRateList(priceRates: PriceRates, basePrice: Double) : Result<List<Pair<String, String>>> {
        val filteredList = priceRates.priceRates.filter { it.key in ratesToUse }
        val convertedPriceList = arrayListOf<Pair<String, String>>()


        filteredList.forEach {
            val convertedPrice = basePrice.times(it.value)
            convertedPriceList.add(Pair(it.key, convertedPrice.formatDecimalDigits(2)))
        }

        return Result<List<Pair<String, String>>>(
            isSuccess = true,
            content = convertedPriceList
        )
    }

}