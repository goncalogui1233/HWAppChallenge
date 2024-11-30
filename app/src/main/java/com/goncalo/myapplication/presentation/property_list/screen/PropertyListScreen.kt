package com.goncalo.myapplication.presentation.property_list.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.goncalo.myapplication.Screens
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.common.UIState
import com.goncalo.myapplication.presentation.common.components.ShimmerEffect
import com.goncalo.myapplication.presentation.common.screens.BuildErrorScreen
import com.goncalo.myapplication.presentation.property_list.viewmodel.PropertyListViewModel
import com.goncalo.myapplication.presentation.property_list.views.BuildPropertyItemFullWidth
import com.goncalo.myapplication.presentation.property_list.views.BuildPropertyItemWrapWidth
import com.goncalo.myapplication.presentation.ui.theme.Color101010

@Composable
fun PropertyListScreen(
    modifier: Modifier = Modifier,
    viewModel: PropertyListViewModel,
    navController: NavController
) {
    Box(modifier = modifier) {
        Column {
            when (val v = viewModel.propertyList.collectAsStateWithLifecycle().value) {
                is UIState.Loading -> {
                    BuildLoadingScreen()
                }

                is UIState.Content -> {
                    BuildContentScreen(navController = navController, propertyList = v.content)
                }

                is UIState.Error -> {
                    BuildErrorScreen(errorMessage = v.errorMessage) {
                        viewModel.getPropertiesList()
                    }
                }

                null -> {
                    // Do nothing
                }
            }
        }
    }

}

@Composable
fun BuildLoadingScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(10) {
            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
fun BuildContentScreen(modifier: Modifier = Modifier, navController: NavController, propertyList: List<Property>) {
    val notFeaturedList = propertyList.filter { it.isPropertyFeatured.not() }
    val featuredList = propertyList.filter { it.isPropertyFeatured }

    LazyColumn(modifier = modifier.padding(horizontal = 10.dp)) {
        item {
            Text(
                text = "Hello there. \uD83D\uDC4B \nCheck out this amazing hostel list.",
                style = TextStyle(color = Color101010, fontSize = 24.sp),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Featured Items", style = TextStyle(color = Color101010, fontSize = 20.sp))
            Featured(navController = navController, list = featuredList)
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Other choices", style = TextStyle(color = Color101010, fontSize = 20.sp))
        }

        items(notFeaturedList) {
            BuildPropertyItemFullWidth(item = it) { id ->
                onItemClicked(navController, id)
            }
        }
    }
}

@Composable
fun Featured(modifier: Modifier = Modifier, navController: NavController, list: List<Property>) {
    LazyRow(modifier = modifier) {
        items(list) {
            BuildPropertyItemWrapWidth(item = it) { id ->
                onItemClicked(navController, id)
            }
        }
    }
}

private fun onItemClicked(navController: NavController, id: Int) {
    navController.navigate(Screens.PropertyDetails.route + "/$id")
}
