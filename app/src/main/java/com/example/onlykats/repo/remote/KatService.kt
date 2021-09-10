package com.example.onlykats.repo.remote

import com.example.onlykats.model.Breed
import com.example.onlykats.model.Category
import com.example.onlykats.model.Kat
import com.example.onlykats.util.Order
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface KatService {

    @Headers("x-api-key: 1fa2c5c0-305d-4305-8edc-2efcbf37b9b4")
    @GET("v1/images/search")
    suspend fun getKatImages2(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: Order,
        @Query("breed_id") breedId: String?,
        @Query("category_ids") categoryIds: String?
    ): Response<List<Kat>>

    suspend fun getKatImages(@QueryMap queryMap: Map<String, String>): Response<List<Kat>>

}