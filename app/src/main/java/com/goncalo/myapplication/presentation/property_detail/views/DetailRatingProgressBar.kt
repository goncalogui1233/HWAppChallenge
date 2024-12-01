package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.common.extensions.formatRatingDecimals
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color4C4DDC
import com.goncalo.myapplication.presentation.ui.theme.Color888787

@Composable
fun BuildProgressItem(modifier: Modifier = Modifier, itemName: String, itemValue: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = itemName,
            modifier = Modifier.weight(1f),
            style = TextStyle(color = Color101010, fontSize = 18.sp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                progress = { itemValue.toFloat().div(100) },
                color = Color4C4DDC,
                trackColor = Color888787,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .width(200.dp)
                    .height(5.dp)
                    .padding(end = 20.dp)
            )

            Text(
                text = itemValue.toDouble().formatRatingDecimals(),
                modifier = Modifier.width(30.dp),
                style = TextStyle(color = Color101010, fontSize = 18.sp)
            )
        }
    }
}