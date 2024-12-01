package com.goncalo.myapplication.presentation.property_list.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.extensions.formatRatingDecimals
import com.goncalo.myapplication.domain.model.getFormattedURL
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color4C4DDC
import com.goncalo.myapplication.presentation.ui.theme.Color888787

@Composable
fun BuildPropertyItemWrapWidth(modifier: Modifier = Modifier, item: Property, onItemClicked: (Int) -> Unit) {
    PropertyListItem(modifier = modifier.wrapContentWidth(), item = item, onItemClicked = onItemClicked)
}

@Composable
fun BuildPropertyItemFullWidth(modifier: Modifier = Modifier, item: Property, onItemClicked: (Int) -> Unit) {
    PropertyListItem(modifier = modifier.fillMaxWidth(), item = item, onItemClicked = onItemClicked)
}

@Composable
private fun PropertyListItem(modifier: Modifier, item: Property, onItemClicked: (Int) -> Unit) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .padding(10.dp)
            .clickable { onItemClicked.invoke(item.propertyId) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White)
    ) {
        Row(modifier = Modifier
            .wrapContentHeight()
            .padding(5.dp)) {

            AsyncImage(
                model = item.imageDataList.first().getFormattedURL(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.propertyName,
                    style = TextStyle(fontSize = 18.sp, color = Color101010)
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color(0xFFffd33c)),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.propertyRating.overallRating.toDouble().formatRatingDecimals(),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }


                BuildItemPrice(
                    price = item.lowPriceNight.priceValue,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun BuildItemPrice(modifier: Modifier = Modifier, price: String) {
    val textStyle = TextStyle(fontSize = 16.sp, color = Color888787)

    Row (modifier = modifier){
        Text(text = stringResource(id = R.string.item_price_starts), style = textStyle)
        Text(text = " $price€", style = textStyle.copy(color = Color4C4DDC))
        Text(text = stringResource(id = R.string.item_price_night), style = textStyle)
    }
}