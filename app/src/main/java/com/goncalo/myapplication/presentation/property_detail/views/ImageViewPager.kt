package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.goncalo.myapplication.domain.model.property.ImageData
import com.goncalo.myapplication.domain.model.property.getFormattedURL

@Composable
fun BuildImageViewPager(modifier: Modifier = Modifier, imageList: List<ImageData>) {
    val pagerState = rememberPagerState(pageCount = {imageList.size})

    val imageWidth = 325.dp
    val paddingValue = 16.dp

    HorizontalPager(
        pageSize = PageSize.Fixed(imageWidth),
        state = pagerState,
        modifier = Modifier.wrapContentWidth(),
        pageSpacing = paddingValue,
        contentPadding = PaddingValues(horizontal = paddingValue)
    ) { page ->
        AsyncImage(
            model = imageList[page].getFormattedURL(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(imageWidth)
                .height(215.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}