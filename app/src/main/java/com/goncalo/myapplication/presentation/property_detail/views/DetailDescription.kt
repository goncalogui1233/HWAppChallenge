package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color888787

@Composable
fun BuildDetailDescription(modifier: Modifier = Modifier, item: Property) {
    val showBottomSheet = remember {
        mutableStateOf(false)
    }

    val mutableInteractionSource = remember {
        MutableInteractionSource()
    }

    Column(modifier = modifier.clickable(interactionSource = mutableInteractionSource, indication = null, onClick =  {
        showBottomSheet.value = true
    })) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.property_details_description),
                style = TextStyle(color = Color101010, fontSize = 22.sp)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.description,
            style = TextStyle(color = Color888787, fontSize = 18.sp),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )

        BuildDescriptionBottomSheet(showBottomSheet = showBottomSheet, item = item)
    }
}