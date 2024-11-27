package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class ImageData(

    @SerializedName("prefix")
    val imagePrefix: String,

    @SerializedName("suffix")
    val imageSuffix: String

)