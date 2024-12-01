package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.Facilities
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.common.components.BuildBottomSheet
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import kotlinx.coroutines.launch

@Composable
fun BuildDetailFacilities(modifier: Modifier = Modifier, item: Property) {

    val mutableInteractionSource = remember {
        MutableInteractionSource()
    }

    val showBottomSheet = remember {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(
            interactionSource = mutableInteractionSource,
            indication = null,
            onClick = {
                showBottomSheet.value = true
            })
    ) {
        Text(
            text = stringResource(id = R.string.property_details_check_facilities),
            style = TextStyle(color = Color101010, fontSize = 16.sp)
        )
        Image(imageVector = Icons.AutoMirrored.Outlined.ArrowForward, contentDescription = null)
    }

    BuildFacilitiesBottomSheet(showBottomSheet = showBottomSheet, item = item)

}


@Composable
fun BuildFacilitiesBottomSheet(modifier: Modifier = Modifier, showBottomSheet: MutableState<Boolean>, item: Property) {

    BuildBottomSheet(showBottomSheet = showBottomSheet) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        val state = rememberLazyListState()
        val scope = rememberCoroutineScope()

        Column {
            Text(text = stringResource(id = R.string.property_details_our_facilities), style = TextStyle(color = Color101010, fontSize = 24.sp), modifier = Modifier.padding(horizontal = 16.dp))

            LazyRow(state = state) {
                itemsIndexed(item.facilitiesList) { index, item ->
                    FilterChip(selected = selectedTabIndex == index, onClick = {
                        selectedTabIndex = index
                        scope.launch {
                            state.animateScrollToItem(index, 10)
                        }
                    }, label = { Text(
                        text = item.facilityName
                    ) }, modifier = Modifier.padding(8.dp))
                }
            }
        }


        BuildFacilitiesList(listFacilities = item.facilitiesList[selectedTabIndex].facilityList)

    }
}


@Composable
fun BuildFacilitiesList(modifier: Modifier = Modifier, listFacilities: ArrayList<Facilities>) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3)) {
        items(listFacilities) {
            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = it.facilityName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    style = TextStyle(
                        color = Color101010,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                )
            }
        }
    }
}