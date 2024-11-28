package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.RateBreakdown
import com.goncalo.myapplication.domain.model.property.Rating
import com.goncalo.myapplication.domain.model.property.convertToPair
import com.goncalo.myapplication.presentation.common.components.BuildProgressItem
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun BuildDetailRating(modifier: Modifier = Modifier, rating: Rating, rateCategory: RateBreakdown) {
    Column(modifier = modifier) {
        Text(
            text = "Rating",
            style = TextStyle(color = Color101010, fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        BuildOverallRating(rating = rating)

        Spacer(modifier = Modifier.height(8.dp))

        rateCategory.convertToPair().forEach { rateItem ->
            BuildProgressItem(itemName = rateItem.first, itemValue = rateItem.second)
        }
    }
}

@Composable
private fun BuildOverallRating(modifier: Modifier = Modifier, rating: Rating) {
    Row(
        modifier = modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFFffd33c)),
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = "${rating.overallRating.div(10.0)}",
            style = TextStyle(color = Color101010, fontSize = 22.sp),
            modifier = Modifier.padding(start = 4.dp)
        )

        Text(
            text = "(${rating.ratingCount} reviews)",
            style = TextStyle(color = Color101010, fontSize = 16.sp),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}