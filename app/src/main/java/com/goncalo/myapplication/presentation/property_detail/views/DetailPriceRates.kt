package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.presentation.property_detail.viewmodel.PropertyDetailViewModel
import com.goncalo.myapplication.presentation.property_list.viewmodel.UIState
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun BuildDetailPriceRates(
    modifier: Modifier = Modifier,
    propertyPrice: Double,
    viewModel: PropertyDetailViewModel
) {
    val v = viewModel.getPriceRates(propertyPrice).subscribeAsState(initial = UIState.Loading)

    when (v.value) {
        is UIState.Loading -> {}
        is UIState.Content -> {
            val content = (v.value as UIState.Content<List<Pair<String, Double>>>).content

            Column(modifier = modifier) {
                Text(
                    text = "Other currencies? We got you",
                    style = TextStyle(color = Color101010, fontSize = 22.sp)
                )

                Text(
                    text = "(lowest price per night)",
                    style = TextStyle(color = Color101010, fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    content.forEach {
                        Card(modifier = Modifier.padding(horizontal = 5.dp)) {
                            Row(modifier = Modifier.padding(all = 15.dp)) {
                                Text(text = it.first)
                                Text(text = it.second.toString())
                            }
                        }
                    }
                }


            }
        }

        is UIState.Error -> {
            // Don't show any information
        }
    }
}