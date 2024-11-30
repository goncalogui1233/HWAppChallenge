package com.goncalo.myapplication.domain.model.rates

import com.goncalo.myapplication.common.extensions.empty
import com.google.gson.annotations.SerializedName

data class PriceRates(

    @SerializedName("timestamp")
    var timestamp: Long = 0L,

    @SerializedName("base")
    var base: String = String().empty(),

    @SerializedName("rates")
    var priceRates: HashMap<String, Double> = hashMapOf()

) {
}