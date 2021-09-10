package com.example.onlykats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlykats.model.Kat
import com.example.onlykats.model.Settings
import com.example.onlykats.repo.KatRepo
import com.example.onlykats.repo.SettingsRepo
import com.example.onlykats.util.ApiState
import com.example.onlykats.util.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class KatViewModel : ViewModel() {

    private val _katState = MutableLiveData<ApiState<List<Kat>>>()
    val katState: LiveData<ApiState<List<Kat>>>
        get() = _katState

    private val _settingsState = MutableLiveData<ApiState<Settings>>()
    val settingsState: LiveData<ApiState<Settings>>
        get() = _settingsState

    private var categoryIds: String? = null
    private var breedId: String? = null
    var limit = 0
    var page = 0
        set(value) {
            if (value > field && isNextPage) fetchKatList(this.limit,this.categoryIds,this.breedId, this.page)
            field = value
        }
    var isNextPage = true

    val TAG = "KatViewModel"

    fun fetchKatList(limit: Int, categoryIds: String?, breedId: String?, page: Int) {

        Log.d(TAG, "fetchKatList: $settingsState")
        
        Log.d(TAG, "fetchKatList: $limit , $categoryIds, $breedId")
        if(limit != this.limit || categoryIds != this.categoryIds || breedId != this.breedId) {
            this.limit = limit
            this.categoryIds = categoryIds
            this.breedId = breedId
            this.page = page
        }

        viewModelScope.launch(Dispatchers.IO) {
            KatRepo.getKatState(
                limit.toString(),
                page.toString(),
                Order.DESC,
                categoryIds,
                breedId)
                .collect { katState ->
                    isNextPage =
                        !(katState is ApiState.Failure && katState.errorMsg == KatRepo.NO_DATA_FOUND)
                    _katState.postValue(katState)
                }
        }
    }

    fun fetchSettingsOptions() {
        viewModelScope.launch(Dispatchers.IO) {
            SettingsRepo.getSettingsState().collect {
                _settingsState.postValue(it)
            }
        }
    }
}