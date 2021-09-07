package com.example.onlykats.repo

import android.util.Log
import com.example.onlykats.repo.remote.RetrofitInstance
import com.example.onlykats.util.ApiState
import com.example.onlykats.util.Order
import kotlinx.coroutines.flow.flow

object KatRepo {
    private const val TAG = "KAT-REPO"

    private val katService by lazy { RetrofitInstance.katService }

    fun getKatState(
        limit: Int,
        page: Int = 1,
        order: Order = Order.DESC
    ) = flow {
        emit(ApiState.Loading)
        Log.d(TAG,"getKatState: emit(ApiState.Loading)")

        Log.d(TAG,"getKatState: katService.GetKatImages(limit,page,order)")
        val katResponse = katService.getKatImages(limit, page, order)
        Log.d(TAG, "getKatState: katResponse = $katResponse")

        val state = if (katResponse.isSuccessful) {
            Log.d(TAG,"getKatState: katResponse.isSuccessful")

            if (katResponse.body().isNullOrEmpty()) {
                Log.d(TAG,"getKatState: no data found")
                ApiState.Failure("No data found.")
            }
            else ApiState.Success(katResponse.body()!!)
        } else ApiState.Failure("Error fetching data.")

        Log.d(TAG,"getKatState: emit(state)")
        emit(state)
    }

}
