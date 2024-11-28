package com.goncalo.myapplication.di

import com.goncalo.myapplication.data.network.IHWAppChallengeApi
import com.goncalo.myapplication.data.repository.NetworkTrackingStatsImpl
import com.goncalo.myapplication.data.repository.PropertyRepositoryImpl
import com.goncalo.myapplication.data.repository.RatesRepositoryImpl
import com.goncalo.myapplication.domain.repository.INetworkTrackRepository
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import com.goncalo.myapplication.domain.repository.IRatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideAPIInterface(
        retrofit: Retrofit
    ) = retrofit.create(IHWAppChallengeApi::class.java)

    @Provides
    @Singleton
    fun providePropertyRepository(api: IHWAppChallengeApi): IPropertyRepository =
        PropertyRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideRatesRepository(api: IHWAppChallengeApi): IRatesRepository = RatesRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideNetworkTrackingStatsRepository(api: IHWAppChallengeApi): INetworkTrackRepository =
        NetworkTrackingStatsImpl(api)

}