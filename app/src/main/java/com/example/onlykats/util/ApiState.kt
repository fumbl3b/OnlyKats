package com.example.onlykats.util

sealed class ApiState<out R> {
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error(val msg: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}