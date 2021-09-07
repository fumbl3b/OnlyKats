package com.example.onlykats.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlykats.model.Kat
import com.example.onlykats.repo.KatRepo
import com.example.onlykats.util.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class KatViewModel() : ViewModel() {

    private val _katState = MutableLiveData<ApiState<List<Kat>>>()
    val katState : MutableLiveData<ApiState<List<Kat>>>
        get() = _katState


    fun fetchKatList(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            KatRepo.getKatState(limit).collect { _katState.postValue(it) }
        }
    }
}