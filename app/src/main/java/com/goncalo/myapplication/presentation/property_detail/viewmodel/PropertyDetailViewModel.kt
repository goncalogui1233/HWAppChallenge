package com.goncalo.myapplication.presentation.property_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.R
import com.goncalo.myapplication.common.Constants
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.use_case.network.GetTrackNetworkStatsUseCase
import com.goncalo.myapplication.domain.use_case.property.GetPropertyUseCase
import com.goncalo.myapplication.domain.use_case.rates.GetRatesUseCase
import com.goncalo.myapplication.presentation.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
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

    private val _coinRates: MutableStateFlow<UIState<List<Pair<String, String>>>?> = MutableStateFlow(null)
    val coinRates = _coinRates.asStateFlow()

    fun getProperty(id: Int) = viewModelScope.launch(Dispatchers.IO) {
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

    fun getPriceRates(propertyPrice: Double) {
        getRatesUseCase(propertyPrice).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
            .subscribe({
                if (it.isSuccess) {
                    it.content?.let { c ->
                        _coinRates.value = UIState.Content(c)
                    }
                } else {
                    _coinRates.value =
                        UIState.Error(it.errorMessage ?: R.string.rate_use_case_error)
                }
            }, {
                _coinRates.value = UIState.Error(R.string.rate_use_case_error)
            })
    }


}