package com.goncalo.myapplication.presentation.common

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Content<T>(val content: T) : UIState<T>()
    data class Error(val errorMessage: String) : UIState<Nothing>()
}