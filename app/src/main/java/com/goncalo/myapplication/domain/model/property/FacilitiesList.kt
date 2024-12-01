package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class FacilitiesList(
    @SerializedName("facilities")
    var facilityList: ArrayList<Facilities> = arrayListOf()
) : Facilities()