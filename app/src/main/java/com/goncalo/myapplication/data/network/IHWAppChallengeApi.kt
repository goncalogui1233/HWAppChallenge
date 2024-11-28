package com.goncalo.myapplication.data.network

import com.goncalo.myapplication.domain.model.property.Properties
import com.goncalo.myapplication.domain.model.rates.PriceRates
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IHWAppChallengeApi {

    @GET("a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563138541/properties.json")
    suspend fun getPropertiesList(): Response<Properties>

    @GET("16e87e40ca7b9650aa8e1b936f23e14e/raw/15efd901b57c2b66bf0201ee7aa9aa2edc6df779/rates.json")
    fun getCoinRates(): Single<PriceRates>

    @GET("6bed011203c6c8217f0d55f74ddcc5c5/raw/ce8f55cfd963aeef70f2ac9f88f34cefd19fca30/stats")
    suspend fun getNetworkTrackingStats(@QueryMap params: HashMap<String, String>): Response<String>
}