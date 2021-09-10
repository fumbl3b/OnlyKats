package com.example.onlykats.repo.remote

import com.example.onlykats.model.Breed
import com.example.onlykats.model.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface SettingsService {

    @Headers("x-api-key: 1fa2c5c0-305d-4305-8edc-2efcbf37b9b4")
    @GET("v1/breeds")
    suspend fun getKatBreeds(): Response<List<Breed>>

    @Headers("x-api-key: 1fa2c5c0-305d-4305-8edc-2efcbf37b9b4")
    @GET("v1/categories")
    suspend fun getKatCategories(): Response<List<Category>>

}