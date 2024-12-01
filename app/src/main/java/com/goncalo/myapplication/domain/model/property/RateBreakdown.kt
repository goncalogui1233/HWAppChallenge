package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class RateBreakdown(

    @SerializedName("security")
    var propertySecurity: Int = 0,

    @SerializedName("location")
    var propertyLocation: Int = 0,

    @SerializedName("staff")
    var propertyStaff: Int = 0,

    @SerializedName("fun")
    var propertyFun: Int = 0,

    @SerializedName("clean")
    var propertyClean: Int = 0,

    @SerializedName("facilities")
    var propertyFacilities: Int = 0,

    @SerializedName("value")
    var propertyValue: Int = 0

)
