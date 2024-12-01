package com.goncalo.myapplication.domain.model.property

import com.goncalo.myapplication.common.extensions.empty
import com.google.gson.annotations.SerializedName

data class Property (

    @SerializedName("id")
    var propertyId: Int = 0,

    @SerializedName("name")
    var propertyName: String = String().empty(),

    @SerializedName("isFeatured")
    var isPropertyFeatured: Boolean = false,

    @SerializedName("overallRating")
    var propertyRating: Rating = Rating(),

    @SerializedName("lowestPricePerNight")
    var lowPriceNight: Price = Price(),

    @SerializedName("imagesGallery")
    var imageDataList: List<ImageData> = arrayListOf(),

    @SerializedName("address1")
    var addressOne: String = String().empty(),

    @SerializedName("address2")
    var addressTwo: String = String().empty(),

    @SerializedName("overview")
    var description: String = String().empty(),

    @SerializedName("ratingBreakdown")
    var rateCategory: RateBreakdown = RateBreakdown(),

    @SerializedName("facilities")
    var facilitiesList: ArrayList<FacilitiesList> = arrayListOf()

)