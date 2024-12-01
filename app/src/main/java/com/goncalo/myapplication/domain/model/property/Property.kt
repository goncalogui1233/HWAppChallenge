package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class Property (

    @SerializedName("id")
    var propertyId: Int,

    @SerializedName("name")
    var propertyName: String,

    @SerializedName("isFeatured")
    var isPropertyFeatured: Boolean,

    @SerializedName("overallRating")
    var propertyRating: Rating = Rating(),

    @SerializedName("lowestPricePerNight")
    var lowPriceNight: Price = Price(),

    @SerializedName("imagesGallery")
    var imageDataList: List<ImageData> = arrayListOf(),

    @SerializedName("address1")
    var addressOne: String,

    @SerializedName("address2")
    var addressTwo: String,

    @SerializedName("overview")
    var description: String,

    @SerializedName("ratingBreakdown")
    var rateCategory: RateBreakdown = RateBreakdown(),

    @SerializedName("facilities")
    var facilitiesList: ArrayList<FacilitiesList> = arrayListOf()

)