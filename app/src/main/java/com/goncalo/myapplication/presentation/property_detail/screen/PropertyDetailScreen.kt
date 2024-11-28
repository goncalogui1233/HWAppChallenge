package com.goncalo.myapplication.presentation.property_detail.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.common.screens.BuildErrorScreen
import com.goncalo.myapplication.presentation.property_detail.viewmodel.PropertyDetailViewModel
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailAddress
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailDescription
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailNamePrice
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailPriceRates
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailRating
import com.goncalo.myapplication.presentation.property_detail.views.BuildImageViewPager
import com.goncalo.myapplication.presentation.property_list.viewmodel.UIState

@Composable
fun PropertyDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    propertyId: Int,
    viewModel: PropertyDetailViewModel
) {

    val v = viewModel.detailUIState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getProperty(propertyId)
    }

    when(v) {
        is UIState.Loading -> {

        }

        is UIState.Content -> BuildDetailScreen(
            modifier = modifier,
            item = v.content,
            navController = navController
        ) {
            BuildDetailPriceRates(
                viewModel = viewModel,
                propertyPrice = v.content.lowPriceNight.priceValue.toDouble()
            )
        }


        is UIState.Error -> BuildErrorScreen(errorMessage = v.errorMessage) {
            viewModel.getProperty(propertyId)
        }


        else -> {
            // Do nothing
        }
    }
}

@Composable
fun BuildDetailScreen(
    modifier: Modifier = Modifier,
    item: Property,
    navController: NavController,
    priceRates: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            }
        }

        Column(modifier = Modifier.scrollable(scrollState, orientation = Orientation.Vertical)) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BuildImageViewPager(imageList = item.imageDataList)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {

                BuildDetailNamePrice(propertyName = item.propertyName, price = item.lowPriceNight)

                Spacer(modifier = Modifier.height(12.dp))

                BuildDetailAddress(addressOne = item.addressOne, addressTwo = item.addressTwo)

                Spacer(modifier = Modifier.height(12.dp))

                BuildDetailDescription(itemDescription = item.description)

                Spacer(modifier = Modifier.height(12.dp))

                BuildDetailRating(
                    rating = item.propertyRating,
                    rateCategory = item.rateCategory
                )

                Spacer(modifier = Modifier.height(12.dp))

                priceRates.invoke()
            }
        }
    }
}