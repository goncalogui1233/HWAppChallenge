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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.common.UIState
import com.goncalo.myapplication.presentation.common.components.ShimmerEffect
import com.goncalo.myapplication.presentation.common.screens.BuildErrorScreen
import com.goncalo.myapplication.presentation.property_detail.viewmodel.PropertyDetailViewModel
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailAddress
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailDescription
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailFacilities
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailNamePrice
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailPriceRates
import com.goncalo.myapplication.presentation.property_detail.views.BuildDetailRating
import com.goncalo.myapplication.presentation.property_detail.views.BuildImageViewPager

@Composable
fun PropertyDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    propertyId: Int,
    viewModel: PropertyDetailViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getProperty(propertyId)
    }

    when(val uiState = viewModel.detailUIState.collectAsStateWithLifecycle().value) {
        is UIState.Loading -> {
            BuildDetailLoadingScreen()
        }

        is UIState.Content -> BuildDetailScreen(
            modifier = modifier,
            item = uiState.content,
            navController = navController
        ) {
            BuildDetailPriceRates(
                viewModel = viewModel,
                propertyPrice = uiState.content.lowPriceNight.priceValue.toDouble()
            )
        }


        is UIState.Error -> BuildErrorScreen(errorMessage = uiState.errorMessage) {
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

    Column(modifier = modifier.verticalScroll(scrollState)) {
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

                Spacer(modifier = Modifier.height(20.dp))

                BuildDetailAddress(addressOne = item.addressOne, addressTwo = item.addressTwo)

                Spacer(modifier = Modifier.height(20.dp))

                BuildDetailDescription(item = item)

                Spacer(modifier = Modifier.height(10.dp))

                BuildDetailFacilities(item = item)

                Spacer(modifier = Modifier.height(20.dp))

                BuildDetailRating(
                    rating = item.propertyRating,
                    rateCategory = item.rateCategory
                )

                Spacer(modifier = Modifier.height(20.dp))

                priceRates.invoke()
            }
        }
    }
}

@Composable
fun BuildDetailLoadingScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            ShimmerEffect(modifier = Modifier
                .width(325.dp)
                .height(215.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp)))
        }

        Row {
            ShimmerEffect(modifier = Modifier
                .width(200.dp)
                .height(25.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(10.dp)))
            Spacer(modifier = modifier.weight(1f))

            ShimmerEffect(modifier = Modifier
                .width(100.dp)
                .height(35.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(10.dp)))
        }

        Spacer(modifier = Modifier.height(12.dp))

        ShimmerEffect(modifier = Modifier
            .width(300.dp)
            .height(25.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp)))

        Spacer(modifier = Modifier.height(12.dp))

        ShimmerEffect(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp)))

        Spacer(modifier = Modifier.height(12.dp))

        ShimmerEffect(modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp)))
    }
}