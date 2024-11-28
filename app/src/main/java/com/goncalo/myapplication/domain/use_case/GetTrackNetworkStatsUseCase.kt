package com.goncalo.myapplication.domain.use_case

import com.goncalo.myapplication.domain.repository.INetworkTrackRepository
import javax.inject.Inject

class GetTrackNetworkStatsUseCase @Inject constructor(
    private val repository: INetworkTrackRepository
) {

    suspend operator fun invoke(action: String, duration: Long) {
        val params = HashMap<String, String>().apply {
            "action" to action
            "duration" to duration
        }

        repository.getNetworkTrackingStats(params)
    }

}