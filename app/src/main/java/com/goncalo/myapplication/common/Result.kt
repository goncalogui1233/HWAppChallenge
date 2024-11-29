package com.goncalo.myapplication.common

data class Result<out T> (
    val isSuccess: Boolean,
    val content: T? = null,
    val requestDuration: Long = 0L,
    val errorMessage: String? = null
)