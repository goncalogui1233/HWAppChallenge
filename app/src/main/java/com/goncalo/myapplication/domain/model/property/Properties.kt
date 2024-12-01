package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("properties")
    var listProperties: ArrayList<Property> = arrayListOf()
)