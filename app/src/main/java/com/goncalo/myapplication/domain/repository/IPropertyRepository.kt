package com.goncalo.myapplication.domain.repository

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.rates.PriceRates
import io.reactivex.rxjava3.core.Single

interface IPropertyRepository {

    suspend fun getPropertiesList(): Properties?

    fun getRates(): Single<PriceRates>

}