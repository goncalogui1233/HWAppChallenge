package com.goncalo.myapplication.domain.use_case.network

import com.goncalo.myapplication.common.Constants
import com.goncalo.myapplication.domain.repository.INetworkTrackRepository
import javax.inject.Inject

class GetTrackNetworkStatsUseCase @Inject constructor(
    private val repository: INetworkTrackRepository
) {

    suspend operator fun invoke(action: String, duration: Long) {
        val params = HashMap<String, String>().apply {
            Constants.ACTION to action
            Constants.DURATION to duration
        }

        repository.getNetworkTrackingStats(params)
    }

}