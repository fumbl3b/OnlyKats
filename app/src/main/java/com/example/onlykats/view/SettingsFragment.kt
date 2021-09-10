package com.example.onlykats.view

import android.nfc.Tag
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
import com.example.onlykats.model.Category
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
        initView()
        setupObservers()
        getSettings()
    }

    private fun initView() = with(binding) {
        sliderLimit.addOnChangeListener { _, value, _ ->
            val isLimitNew = value.toInt() != katViewModel.limit
            toggleApply(isLimitNew)
        }
        btnApply.setOnClickListener {
            katViewModel.limit = sliderLimit.value.toInt()
            katViewModel.fetchKatList()
        }
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

    private fun handleSuccess(settings: Settings) {


        // TODO: figure out how to have the id tied in here
        val breedsList = ArrayList<String>()
        for (breed in settings.breeds!!) {
            breed.name?.let { breedsList.add(it) }
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.breed_list_item, breedsList)
        (binding.breedMenuEditText as? AutoCompleteTextView)?.setAdapter(adapter)

        val categoriesList = ArrayList<Category>()
    }

    private fun toggleApply(dataChanged: Boolean) {
        binding.btnApply.isVisible = dataChanged
    }
}