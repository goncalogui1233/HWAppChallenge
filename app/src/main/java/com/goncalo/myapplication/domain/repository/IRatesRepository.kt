package com.goncalo.myapplication.domain.repository

import com.goncalo.myapplication.domain.model.rates.PriceRates

interface IRatesRepository {

    suspend fun getRates(): PriceRates

}