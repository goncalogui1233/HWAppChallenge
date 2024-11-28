package com.goncalo.myapplication.data.repository

import com.goncalo.myapplication.data.network.IHWAppChallengeApi
import com.goncalo.myapplication.domain.repository.INetworkTrackRepository
import javax.inject.Inject

class NetworkTrackingStatsImpl @Inject constructor(
    private val api: IHWAppChallengeApi
) : INetworkTrackRepository {

    override suspend fun getNetworkTrackingStats(params: HashMap<String, String>) {
        api.getNetworkTrackingStats(params)
    }

}