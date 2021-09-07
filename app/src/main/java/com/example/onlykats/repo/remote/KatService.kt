package com.example.onlykats.repo.remote

import com.example.onlykats.model.Kat
import com.example.onlykats.util.Order
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KatService {

    @Headers("x-api-key: ce2b30e0-4e86-48aa-a13c-8167ca0e4857")
    @GET("v1/images/search")
    suspend fun getKatImages(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: Order
    ): Response<List<Kat>>

}