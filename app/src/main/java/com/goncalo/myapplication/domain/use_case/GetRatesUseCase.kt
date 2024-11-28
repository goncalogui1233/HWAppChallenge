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

    operator fun invoke(basePrice: Double): Single<List<Pair<String, Double>>> {
         return repository.getRates().map {
             filterRateList(it, basePrice)
         }
    }

    private fun filterRateList(priceRates: PriceRates, basePrice: Double) : List<Pair<String, Double>> {
        val filteredList = priceRates.priceRates.filter { it.key in ratesToUse }
        val convertedPriceList = arrayListOf<Pair<String, Double>>()
        val decimalFormat = DecimalFormat("#.##")

        filteredList.forEach {
            val v = decimalFormat.format(it.value).toDouble()
            val convertedPrice = basePrice.times(v)
            convertedPriceList.add(Pair(it.key, decimalFormat.format(convertedPrice).toDouble()))
        }

        return convertedPriceList
    }

}