package com.goncalo.myapplication.presentation.property_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.common.Constants
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.use_case.network.GetTrackNetworkStatsUseCase
import com.goncalo.myapplication.domain.use_case.property.GetPropertyListUseCase
import com.goncalo.myapplication.presentation.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val getPropertyListUseCase: GetPropertyListUseCase,
    private val getTrackNetworkStatsUseCase: GetTrackNetworkStatsUseCase
) : ViewModel() {

    private val _propertyList: MutableStateFlow<UIState<List<Property>>?> = MutableStateFlow(null)
    val propertyList = _propertyList.asStateFlow()

    init {
        getPropertiesList()
    }

    fun getPropertiesList() = viewModelScope.launch(Dispatchers.IO) {
        _propertyList.emit(UIState.Loading)

        with(getPropertyListUseCase()) {
            if(isSuccess) {
                _propertyList.emit(UIState.Content(content ?: arrayListOf()))
                getTrackNetworkStatsUseCase(Constants.LOAD_LIST, this.requestDuration)
            } else {
                _propertyList.emit(UIState.Error(errorMessage.orEmpty()))
            }
        }
        //_propertyList.emit(UIState.Error("Oh no, an error appeared whild loading information. \nPlease, try again"))
    }
}