package com.goncalo.myapplication.domain.repository

import com.goncalo.myapplication.domain.model.rates.PriceRates
import io.reactivex.rxjava3.core.Single

interface IRatesRepository {

    fun getRates(): Single<PriceRates>

}