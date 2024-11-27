package com.goncalo.myapplication.presentation.property_list.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color4C4DDC
import com.goncalo.myapplication.presentation.ui.theme.ColorE1E1E1

@Composable
fun BuildPropertyItemWrapWidth(modifier: Modifier = Modifier, item: Property) {
    PropertyListItem(modifier = modifier.wrapContentWidth(), item = item)
}

@Composable
fun BuildPropertyItemFullWidth(modifier: Modifier = Modifier, item: Property) {
    PropertyListItem(modifier = modifier.fillMaxWidth(), item = item)
}

@Composable
private fun PropertyListItem(modifier: Modifier, item: Property) {
    Card(
        modifier = modifier.wrapContentHeight().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White)
    ) {
        Row(modifier = Modifier
            .wrapContentHeight()
            .padding(5.dp)) {

            AsyncImage(
                model = "https://${item.imageDataList.first().imagePrefix}${item.imageDataList.first().imageSuffix}",
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
                        text = "${item.propertyRating.overallRating.div(10.0)}",
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
    val textStyle = TextStyle(fontSize = 16.sp, color = ColorE1E1E1)

    Row (modifier = modifier){
        Text(text = "Starts at ", style = textStyle)
        Text(text = "$price€", style = textStyle.copy(color = Color4C4DDC))
        Text(text = "/night", style = textStyle)
    }
}