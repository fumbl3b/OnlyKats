package com.example.onlykats.repo

import android.util.Log
import com.example.onlykats.model.Kat
import com.example.onlykats.model.KatResponse
import com.example.onlykats.repo.remote.KatService
import com.example.onlykats.repo.remote.RetrofitInstance

object KatRepo {
    private const val TAG = "KAT-REPO"

    private val katService by lazy { RetrofitInstance.katService }

    suspend fun getKats(limit: Int, page: Int): List<Kat>? {

        val katResult = katService.getKatImages(limit, page)

        return if (katResult.isSuccessful) {
            Log.e(TAG, "getKats: was successful. Got Kats.")
            katResult.body()?.katList ?: listOf()
        } else {
            Log.e(TAG, "getKats: Sorry error, ${katResult.errorBody()}")
            null
        }
    }

}
