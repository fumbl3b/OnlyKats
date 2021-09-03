package com.example.onlykats.repo

import android.util.Log
import com.example.onlykats.model.Kat
import com.example.onlykats.model.KatResponse
import com.example.onlykats.repo.remote.RetrofitInstance

class KatRepo(private val instance: RetrofitInstance) {

    val TAG = "KatRepo"

    suspend fun getKats(limit: Int, page: Int, order: String): List<Kat>? {
        val katResult = RetrofitInstance.katService.getKatImages(limit, page, order)

        return if (katResult.isSuccessful) {
            Log.e(TAG, "getKats: was successful. Got Kats.")
            katResult.body()?.katList ?: listOf()
        } else {
            Log.e(TAG, "getKats: Sorry error, ${katResult.errorBody()}")
            null
        }
    }

}
