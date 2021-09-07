package com.example.onlykats.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kat(
    val approved: Int?,
    val breeds: List<Breed> = listOf(),
    val categories: List<Category> = listOf(),
    val height: Int?,
    val id: String,
    val pending: Int?,
    val rejected: Int?,
    val url: String,
    val width: Int?
)