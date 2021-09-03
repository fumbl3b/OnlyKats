package com.example.onlykats.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlykats.model.Kat
import com.example.onlykats.model.KatResponse
import com.example.onlykats.repo.KatRepo
import com.example.onlykats.repo.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class KatViewModel(val katRepo: KatRepo) : ViewModel() {

class KatViewModel() : ViewModel() {

    private val katRepo: KatRepo = KatRepo(RetrofitInstance)
    private val _katList: MutableLiveData<List<Kat>> = MutableLiveData()
    val katList: LiveData<List<Kat>> get() = _katList

    fun getKats(limit: Int, page: Int, order: String) = viewModelScope.launch(Dispatchers.IO) {

//        withContext(Dispatchers.Main) {
//            _katList.value = katRepo.getKats(limit, page, order)
//        }

        _katList.postValue(
            katRepo.getKats(limit, page, order)
        )

    }

    companion object {
        fun newInstance():KatViewModel {
            return KatViewModel()
        }
    }
}