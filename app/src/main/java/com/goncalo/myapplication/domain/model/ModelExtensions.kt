package com.goncalo.myapplication.domain.model

import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.ImageData
import com.goncalo.myapplication.domain.model.property.RateBreakdown

/**
 * Extension that generates a key-value pair with the following content
 * First - Item name that will be used on screen
 * Second - The respective rating
 */
fun RateBreakdown.convertToPair() : List<Pair<Int, Int>> {
    return arrayListOf<Pair<Int, Int>>().apply {
        add(Pair(R.string.rate_security, this@convertToPair.propertySecurity))
        add(Pair(R.string.rate_location, this@convertToPair.propertyLocation))
        add(Pair(R.string.rate_staff, this@convertToPair.propertyStaff))
        add(Pair(R.string.rate_atmosphere, this@convertToPair.propertyFun))
        add(Pair(R.string.rate_cleaning, this@convertToPair.propertyClean))
        add(Pair(R.string.rate_facilities, this@convertToPair.propertyFacilities))
        add(Pair(R.string.rate_value, this@convertToPair.propertyValue))
    }
}

fun ImageData.getFormattedURL() = "https://${imagePrefix}${imageSuffix}"