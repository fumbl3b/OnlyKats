package com.example.onlykats.util

import com.example.onlykats.model.Kat

sealed class ApiState {
    data class Success(val data: List<Kat>)
    data class Error(val msg: String)
    object Loading
}