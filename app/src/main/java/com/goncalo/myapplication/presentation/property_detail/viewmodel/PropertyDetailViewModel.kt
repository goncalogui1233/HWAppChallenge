package com.goncalo.myapplication.presentation.property_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.use_case.GetPropertyUseCase
import com.goncalo.myapplication.domain.use_case.GetRatesUseCase
import com.goncalo.myapplication.presentation.property_list.viewmodel.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val getPropertyUseCase: GetPropertyUseCase,
    private val getRatesUseCase: GetRatesUseCase
): ViewModel() {

    private val _detailUiState: MutableStateFlow<UIState<Property>?> = MutableStateFlow(null)
    val detailUIState = _detailUiState.asStateFlow()

    fun getProperty(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        _detailUiState.value = (UIState.Loading)

        getPropertyUseCase(id)?.let { property ->
            _detailUiState.value = (UIState.Content(property))
        } ?: run {
            _detailUiState.value = (UIState.Error("Error"))
        }
    }

    fun getPriceRates(propertyPrice: Double) = getRatesUseCase(propertyPrice).flatMap {
        Single.just(UIState.Content(it))
    }



}