package com.goncalo.myapplication.domain.use_case

import com.goncalo.myapplication.domain.model.rates.PriceRates
import com.goncalo.myapplication.domain.repository.IRatesRepository
import io.reactivex.rxjava3.core.Single
import java.text.DecimalFormat
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(
    private val repository: IRatesRepository
) {
    private val ratesToUse = listOf("EUR", "USD", "GBP")

    operator fun invoke(basePrice: Double): Single<Result<List<Pair<String, Double>>>> {
        return repository.getRates().map {
            filterRateList(it, basePrice)
        }.onErrorReturn {
            Result(
                isSuccess = false,
                errorMessage = "Null"
            )
        }
    }

    private fun filterRateList(priceRates: PriceRates, basePrice: Double) : Result<List<Pair<String, Double>>> {
        val filteredList = priceRates.priceRates.filter { it.key in ratesToUse }
        val convertedPriceList = arrayListOf<Pair<String, Double>>()
        val decimalFormat = DecimalFormat("#.##")

        filteredList.forEach {
            val v = decimalFormat.format(it.value).toDouble()
            val convertedPrice = basePrice.times(v)
            convertedPriceList.add(Pair(it.key, decimalFormat.format(convertedPrice).toDouble()))
        }

        return Result<List<Pair<String, Double>>>(
            isSuccess = true,
            content = convertedPriceList
        )
    }

}