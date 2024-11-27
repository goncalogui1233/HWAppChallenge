package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class Property (

    @SerializedName("name")
    var propertyName: String,

    @SerializedName("isFeatured")
    var isPropertyFeatured: Boolean,

    @SerializedName("overallRating")
    var propertyRating: Rating = Rating(),

    @SerializedName("lowestPricePerNight")
    var lowPriceNight: Price = Price(),

    @SerializedName("imagesGallery")
    var imageDataList: List<ImageData>

)