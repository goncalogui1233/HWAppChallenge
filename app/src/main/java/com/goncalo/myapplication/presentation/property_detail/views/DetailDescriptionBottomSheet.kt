package com.goncalo.myapplication.presentation.property_detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goncalo.myapplication.R
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.presentation.common.components.BuildBottomSheet
import com.goncalo.myapplication.presentation.ui.theme.Color101010
import com.goncalo.myapplication.presentation.ui.theme.Color888787

@Composable
fun BuildDescriptionBottomSheet(modifier: Modifier = Modifier, showBottomSheet: MutableState<Boolean>, item: Property) {
    BuildBottomSheet(showBottomSheet = showBottomSheet) {
        val scrollState = rememberScrollState()

        Column(modifier = modifier.padding(horizontal = 16.dp).verticalScroll(state = scrollState)) {
            Text(
                text = stringResource(id = R.string.property_details_description),
                style = TextStyle(color = Color101010, fontSize = 22.sp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                style = TextStyle(color = Color888787, fontSize = 18.sp),
            )
        }
    }
}