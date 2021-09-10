package com.example.onlykats.repo

import android.util.Log
import com.example.onlykats.repo.remote.RetrofitInstance
import com.example.onlykats.util.ApiState
import com.example.onlykats.util.Order
import kotlinx.coroutines.flow.flow

object KatRepo {
    private const val TAG = "KAT-REPO"

    const val NO_DATA_FOUND = "No data found."
    private val katService by lazy { RetrofitInstance.katService }

    fun getKatState(
        limit: String,
        page: String = "1",
        order: Order = Order.DESC,
        breedId: String? ,
        categoryIds: String?
    ) = flow {
        emit(ApiState.Loading)

        val queryMap = listOfNotNull(
                limit?.let { "limit" to it },
                page?.let { "page" to it },
                order?.toString().let { "order" to it },
                breedId?.let { "breed_id" to it },
                categoryIds?.let { "category_ids" to it }
        ).toMap()

        Log.d(TAG, "getKatState: ${queryMap.entries}")
        val katResponse = katService.getKatImages(queryMap)

        val state = if (katResponse.isSuccessful) {
            if (katResponse.body().isNullOrEmpty()) {
                ApiState.Failure(NO_DATA_FOUND)
            } else {
                ApiState.Success(katResponse.body()!!)
            }
        } else {
            ApiState.Failure("Error fetching data.")
        }

        emit(state)
    }

}