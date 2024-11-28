package com.goncalo.myapplication.presentation.common.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun BuildErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    retryButtonAction: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = errorMessage,
            style = TextStyle(
                color = Color101010,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 8.dp)

        )

        Button(onClick = retryButtonAction, modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "Retry",
            )
        }
    }
}