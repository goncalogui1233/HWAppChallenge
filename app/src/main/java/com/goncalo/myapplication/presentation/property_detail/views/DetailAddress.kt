package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.common.extensions.concatAddress
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun BuildDetailAddress(modifier: Modifier = Modifier, addressOne: String, addressTwo: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(imageVector = Icons.Outlined.LocationOn, contentDescription = null)

        Text(
            text = addressOne.concatAddress(addressTwo),
            style = TextStyle(color = Color101010, fontSize = 18.sp),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}