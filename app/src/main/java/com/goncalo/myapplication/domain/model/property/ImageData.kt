package com.goncalo.myapplication.domain.model.property

import com.goncalo.myapplication.common.extensions.empty
import com.google.gson.annotations.SerializedName

data class ImageData(

    @SerializedName("prefix")
    val imagePrefix: String = String().empty(),

    @SerializedName("suffix")
    val imageSuffix: String = String().empty()

)