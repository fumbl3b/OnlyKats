package com.example.onlykats.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentSettingsBinding
import com.example.onlykats.model.Settings
import com.example.onlykats.util.ApiState
import com.example.onlykats.viewmodel.KatViewModel

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    val TAG = "SettingsFragment"

    private lateinit var binding: FragmentSettingsBinding
    private val katViewModel by activityViewModels<KatViewModel>()
    private lateinit var breedsMap: Map<String, String>
    private val categoryMap: Map<String, String> = mutableMapOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initView()
        setupObservers()
    }

    private fun initView() = with(binding) {
        getSettings()
        sliderLimit.value = katViewModel.limit.toFloat()

    }

    private fun translateTextToId(str: String, map: Map<String, String>): String {
        val res = map.getValue(str)
        Log.d(TAG, "translateTextToId: $res")
        return res
    }

    private fun getSettings() = with(binding){
        sliderLimit.value = katViewModel.limit.toFloat()
        katViewModel.fetchSettingsOptions()
        Log.d(TAG, "FETCHING SETTINGS")
    }

    private fun setupObservers() = with(katViewModel) {
        settingsState.observe(viewLifecycleOwner) { state ->
            if (state is ApiState.Success) {
                Log.d(TAG, "SETTINGS RECEIVED SUCCESS")
                handleSuccess(state.data)
            }
            if (state is ApiState.Failure) Log.d(TAG, "SETTINGS RECEIVED FAILURE")
        }
    }

    private fun handleSuccess(settings: Settings) = with(binding) {

        val bm = mutableMapOf<String,String>()
        val cm = mutableMapOf<String,String>()

        for (breed in settings.breeds!!) {
            if (breed.name != null && breed.id != null) bm[breed.name] = breed.id
        }
        for (category in settings.categories!!) {
            if( category.name != null && category.id != null) cm[category.name] = category.id.toString()
        }

        btnApply.setOnClickListener {
            val b = if (breedMenuEditText?.text.toString().isNullOrEmpty()) null
                else translateTextToId(breedMenuEditText?.text.toString(), bm)
            val c = if (categoryMenuEditText?.text.toString().isNullOrEmpty()) null
                else translateTextToId(categoryMenuEditText?.text.toString(), cm)
            katViewModel.fetchKatList(sliderLimit.value.toInt(),b, c, 0)
        }

        val breedsList = ArrayList<String>()
        for (breed in settings.breeds!!) {
            breed.name?.let { breedsList.add(it) }
        }
        val breedAdapter = ArrayAdapter(requireContext(), R.layout.setting_list_item, breedsList)
        (breedMenuEditText as? AutoCompleteTextView)?.setAdapter(breedAdapter)

        val categoriesList = ArrayList<String>()
        for (category in settings.categories!!) {
            category.name?.let { categoriesList.add(it) }
        }
        val categoryAdapter = ArrayAdapter(requireContext(), R.layout.setting_list_item, categoriesList)
        (categoryMenuEditText as? AutoCompleteTextView)?.setAdapter(categoryAdapter)

    }
}