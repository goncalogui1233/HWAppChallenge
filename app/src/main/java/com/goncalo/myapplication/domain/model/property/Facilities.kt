package com.goncalo.myapplication.domain.model.property

import com.goncalo.myapplication.common.extensions.empty
import com.google.gson.annotations.SerializedName

open class Facilities(
    @SerializedName("name")
    var facilityName: String = String().empty(),

    @SerializedName("id")
    var facilityId: String = String().empty()
)