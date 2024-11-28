package com.goncalo.myapplication.data.network

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.rates.PriceRates
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface IHWAppChallengeApi {

    @GET("a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563138541/properties.json")
    suspend fun getPropertiesList(): Response<Properties>

    @GET("16e87e40ca7b9650aa8e1b936f23e14e/raw/15efd901b57c2b66bf0201ee7aa9aa2edc6df779/rates.json")
    fun getCoinRates(): Single<PriceRates>

}