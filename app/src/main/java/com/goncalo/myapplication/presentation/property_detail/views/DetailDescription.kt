package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.ColorE1E1E1

@Composable
fun BuildDetailDescription(modifier: Modifier = Modifier, itemDescription: String) {
    Column(modifier = modifier) {
        Text(
            text = "Description",
            style = TextStyle(color = Color101010, fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = itemDescription,
            style = TextStyle(color = ColorE1E1E1, fontSize = 18.sp),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
    }
}