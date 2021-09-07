package com.example.onlykats.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    val id: Int?,
    val name: String?
)