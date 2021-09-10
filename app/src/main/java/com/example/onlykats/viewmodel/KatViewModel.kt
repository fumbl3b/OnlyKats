package com.example.onlykats.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlykats.model.Breed
import com.example.onlykats.model.Category
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
            if (value > field && isNextPage) fetchKatList()
            field = value
        }
    var isNextPage = true


    fun fetchKatList() {
        viewModelScope.launch(Dispatchers.IO) {
            KatRepo.getKatState(limit.toString(), page.toString(), Order.DESC, categoryIds, breedId)
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