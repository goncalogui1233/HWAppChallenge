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

/**
 * Extension that generates a key-value pair with the following content
 * First - Item name that will be used on screen
 * Second - The respective rating
 */
fun RateBreakdown.convertToPair() : List<Pair<String, Int>> {
    return arrayListOf<Pair<String, Int>>().apply {
        add(Pair("Security", this@convertToPair.propertySecurity))
        add(Pair("Location", this@convertToPair.propertyLocation))
        add(Pair("Staff", this@convertToPair.propertyStaff))
        add(Pair("Atmosphere", this@convertToPair.propertyFun))
        add(Pair("Cleaning", this@convertToPair.propertyClean))
        add(Pair("Facilities", this@convertToPair.propertyFacilities))
        add(Pair("Value", this@convertToPair.propertyValue))
    }
}
