package com.goncalo.myapplication.presentation.property_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.use_case.GetPropertyUseCase
import com.goncalo.myapplication.domain.use_case.GetRatesUseCase
import com.goncalo.myapplication.domain.use_case.GetTrackNetworkStatsUseCase
import com.goncalo.myapplication.presentation.property_list.viewmodel.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val getPropertyUseCase: GetPropertyUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    private val getTrackNetworkStatsUseCase: GetTrackNetworkStatsUseCase,
): ViewModel() {

    private val _detailUiState: MutableStateFlow<UIState<Property>?> = MutableStateFlow(null)
    val detailUIState = _detailUiState.asStateFlow()

    fun getProperty(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _detailUiState.value = UIState.Loading

        with(getPropertyUseCase(id)) {
            if (isSuccess) {
                content?.let {
                    _detailUiState.value = UIState.Content(content)
                }
                getTrackNetworkStatsUseCase("load-details", requestDuration)
            } else {
                _detailUiState.value = UIState.Error(errorMessage.orEmpty())
            }
        }
    }

    fun getPriceRates(propertyPrice: Double) = getRatesUseCase(propertyPrice).map {
        if(it.isSuccess) {
            UIState.Content(it.content)
        } else {
            UIState.Error("")
        }
    }

}