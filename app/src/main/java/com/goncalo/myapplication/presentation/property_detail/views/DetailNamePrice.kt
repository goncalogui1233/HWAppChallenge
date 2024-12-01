package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.Price
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color4C4DDC

@Composable
fun BuildDetailNamePrice(modifier: Modifier = Modifier, propertyName: String, price: Price) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = propertyName,
            style = TextStyle(color = Color101010, fontSize = 26.sp),
            modifier = Modifier.weight(1f)
        )

        BuildDetailPrice(price = price)
    }
}

@Composable
private fun BuildDetailPrice(modifier: Modifier = Modifier, price: Price) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        Text(
            text = "${price.priceCurrency} ${price.priceValue}",
            style = TextStyle(
                color = Color4C4DDC,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = stringResource(id = R.string.property_details_lowest_price),
            style = TextStyle(color = Color101010, fontSize = 14.sp)
        )
    }
}