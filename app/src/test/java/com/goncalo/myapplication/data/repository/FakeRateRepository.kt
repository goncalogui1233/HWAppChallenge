package com.goncalo.myapplication.data.repository

import com.goncalo.myapplication.domain.model.rates.PriceRates
import com.goncalo.myapplication.domain.repository.IRatesRepository
import io.reactivex.rxjava3.core.Single

class FakeRateRepository: IRatesRepository {

    val rate = PriceRates()

    override fun getRates(): Single<PriceRates> {
        return Single.just(rate)
    }

    fun addNewRate(key: String, value: Double) {
        rate.priceRates[key] = value
    }

    fun removeRates() {
        rate.priceRates.clear()
    }

}