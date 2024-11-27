package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("value")
    var priceValue: String = "0.0",

    @SerializedName("currency")
    var priceCurrency: String = "EUR"
)