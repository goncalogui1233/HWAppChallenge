package com.goncalo.myapplication.domain.model.rates

import com.google.gson.annotations.SerializedName

data class PriceRates(

    @SerializedName("timestamp")
    var timestamp: Long = 0L,

    @SerializedName("base")
    var base: String,

    @SerializedName("rates")
    var priceRates: Map<String, Double> = mapOf()

) {
}