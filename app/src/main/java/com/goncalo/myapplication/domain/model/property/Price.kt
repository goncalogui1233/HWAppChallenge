package com.goncalo.myapplication.domain.model.property

import com.goncalo.myapplication.common.Constants
import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("value")
    var priceValue: String = Constants.ZERO_VALUE,

    @SerializedName("currency")
    var priceCurrency: String = Constants.EUR
)