package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.extensions.formatDecimalDigits
import com.goncalo.myapplication.presentation.common.UIState
import com.goncalo.myapplication.presentation.common.components.ShimmerEffect
import com.goncalo.myapplication.presentation.property_detail.viewmodel.PropertyDetailViewModel
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun BuildDetailPriceRates(
    modifier: Modifier = Modifier,
    propertyPrice: Double,
    viewModel: PropertyDetailViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getPriceRates(propertyPrice)
    }

    val v = viewModel.coinRates.collectAsStateWithLifecycle(initialValue = null)

    when (v.value) {
        is UIState.Loading -> {
            BuildPriceRatesLoading()
        }
        is UIState.Content -> {
            val rateList = (v.value as UIState.Content<HashMap<String, String>>).content
            BuildPriceRateContent(rateList = rateList)
        }

        is UIState.Error -> {
            // Don't show any information
        }

        else -> {

        }
    }
}

@Composable
fun BuildPriceRatesLoading(modifier: Modifier = Modifier) {
    Column {
        ShimmerEffect(modifier = Modifier
            .width(200.dp)
            .height(20.dp))

        Row(modifier = modifier.padding(top = 10.dp)) {
            ShimmerEffect(modifier = Modifier
                .weight(1f)
                .height(30.dp)
                .padding(end = 10.dp))

            ShimmerEffect(modifier = Modifier
                .weight(1f)
                .height(30.dp)
                .padding(horizontal = 15.dp))

            ShimmerEffect(modifier = Modifier
                .weight(1f)
                .height(30.dp)
                .padding(start = 10.dp))
        }
    }
}

@Composable
fun BuildPriceRateContent(modifier: Modifier = Modifier, rateList: HashMap<String, String>) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.property_details_other_currencies),
            style = TextStyle(color = Color101010, fontSize = 22.sp)
        )

        Text(
            text = stringResource(id = R.string.property_details_lowest_price),
            style = TextStyle(color = Color101010, fontSize = 14.sp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        LazyRow {
            items(rateList.toList()) {
                Card(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Row(modifier = Modifier.padding(all = 15.dp)) {
                        Text(text = it.first)
                        Text(text = it.second.toDouble().formatDecimalDigits(2))
                    }
                }
            }
        }
    }
}