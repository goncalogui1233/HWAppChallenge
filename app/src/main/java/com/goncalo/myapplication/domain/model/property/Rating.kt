package com.goncalo.myapplication.domain.model.property

import com.google.gson.annotations.SerializedName

data class Rating (
    @SerializedName("overall")
    var overallRating: Int = 0,

    @SerializedName("numberOfRatings")
    var ratingCount: Int = 0
)