package com.goncalo.myapplication.presentation.property_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goncalo.myapplication.domain.model.property.Property
import com.goncalo.myapplication.domain.repository.IPropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val repository: IPropertyRepository
) : ViewModel() {

    private val _propertyList: MutableStateFlow<UIState<List<Property>>?> = MutableStateFlow(null)
    val propertyList = _propertyList.asStateFlow()

    init {
        getPropertiesList()
    }

    fun getPropertiesList() = viewModelScope.launch(Dispatchers.IO) {
        _propertyList.emit(UIState.Loading)
        repository.getPropertiesList()?.let {
            _propertyList.emit(UIState.Content(it.listProperties))
        } ?: kotlin.run {
            _propertyList.emit(UIState.Error("Oh no, an error appeared whild loading information. \nPlease, try again"))
        }
    }
}


sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Content<T>(val content: T) : UIState<T>()
    data class Error(val errorMessage: String) : UIState<Nothing>()
}