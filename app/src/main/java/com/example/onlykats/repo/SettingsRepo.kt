package com.example.onlykats.repo

import com.example.onlykats.model.Breed
import com.example.onlykats.model.Category
import com.example.onlykats.model.Settings
import com.example.onlykats.repo.remote.RetrofitInstance
import com.example.onlykats.repo.remote.RetrofitInstance.katService
import com.example.onlykats.repo.remote.SettingsService
import com.example.onlykats.util.ApiState
import com.example.onlykats.util.Order
import kotlinx.coroutines.flow.flow


object SettingsRepo {
    private const val TAG = "SETTING-REPO"

    private const val NO_DATA_FOUND = "No data found."

    private lateinit var settingsState: ApiState<Settings>

    private val settingsService by lazy { RetrofitInstance.settingsService }

    fun getSettingsState() = flow {
        emit(ApiState.Loading)

        val katBreedsResponse = settingsService.getKatBreeds()
        val katCategoriesResponse = settingsService.getKatCategories()

        if (katBreedsResponse.isSuccessful && katCategoriesResponse.isSuccessful) {
            if (katBreedsResponse.body().isNullOrEmpty() || katCategoriesResponse.body()
                    .isNullOrEmpty()
            ) {
                emit(ApiState.Failure(NO_DATA_FOUND))
            } else {
                settingsState = ApiState.Success(
                    Settings(
                        katCategoriesResponse.body()!!,
                        katBreedsResponse.body()!!
                    )
                )
            }
        } else emit(ApiState.Failure("Error fetching data."))

        emit(settingsState)
    }

}