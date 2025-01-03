package com.goncalo.myapplication.data.repository

import com.goncalo.myapplication.data.network.IHWAppChallengeApi
import com.goncalo.myapplication.domain.model.rates.PriceRates
import com.goncalo.myapplication.domain.repository.IRatesRepository
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val api: IHWAppChallengeApi
) : IRatesRepository {

    override suspend fun getRates(): PriceRates {
        return api.getCoinRates()
    }

}