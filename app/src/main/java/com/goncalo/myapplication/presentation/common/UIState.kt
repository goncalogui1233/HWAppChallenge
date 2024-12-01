package com.goncalo.myapplication.presentation.common

import androidx.annotation.StringRes

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Content<T>(val content: T) : UIState<T>()
    data class Error(@StringRes val errorMessage: Int) : UIState<Nothing>()
}