package com.goncalo.myapplication.domain.repository

interface INetworkTrackRepository {

    suspend fun getNetworkTrackingStats(params: HashMap<String, String>)

}