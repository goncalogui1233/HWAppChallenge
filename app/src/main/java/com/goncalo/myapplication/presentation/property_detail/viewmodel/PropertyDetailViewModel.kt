package com.goncalo.myapplication.presentation.property_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Constants
import com.goncalo.myapplication.di.IoDispatcher
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.use_case.network.GetTrackNetworkStatsUseCase
import com.goncalo.myapplication.domain.use_case.property.GetPropertyUseCase
import com.goncalo.myapplication.domain.use_case.rates.GetRatesUseCase
import com.goncalo.myapplication.presentation.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val getPropertyUseCase: GetPropertyUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    private val getTrackNetworkStatsUseCase: GetTrackNetworkStatsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _detailUiState: MutableStateFlow<UIState<Property>?> = MutableStateFlow(null)
    val detailUIState = _detailUiState.asStateFlow()

    private val _coinRates: MutableStateFlow<UIState<HashMap<String, String>>?> = MutableStateFlow(null)
    val coinRates = _coinRates.asStateFlow()

    fun getProperty(id: Int) = viewModelScope.launch(ioDispatcher) {
        _detailUiState.value = UIState.Loading

        with(getPropertyUseCase(id)) {
            if (isSuccess) {
                content?.let {
                    _detailUiState.value = UIState.Content(content)
                }
                getTrackNetworkStatsUseCase(Constants.LOAD_DETAILS, requestDuration)
            } else {
                _detailUiState.value =
                    UIState.Error(errorMessage ?: R.string.property_use_case_error)
            }
        }
    }

    fun getPriceRates(propertyPrice: Double) = viewModelScope.launch(ioDispatcher) {
        with(getRatesUseCase(propertyPrice)) {
            if (isSuccess) {
                _coinRates.value = UIState.Content(content ?: hashMapOf())
            } else {
                UIState.Error(errorMessage ?: R.string.rate_use_case_error)
            }
        }
    }
}