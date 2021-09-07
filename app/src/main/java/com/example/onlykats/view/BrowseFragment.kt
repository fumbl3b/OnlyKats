package com.example.onlykats.view

import android.os.Bundle
import android.util.Log
import android.view.ActionProvider
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentBrowseBinding
import com.example.onlykats.model.Kat
import com.example.onlykats.util.ApiState
import com.example.onlykats.view.adapter.KatAdapter
import com.example.onlykats.viewModel.KatViewModel


class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    private val katViewModel by viewModels<KatViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)
        initViews()
        setupObservers()

        katViewModel.fetchKatList(10)
    }

    private fun initViews() = with(binding) {
        rvKats.adapter = KatAdapter()
        settingsButton.setOnClickListener()
    }

    private fun setupObservers() = with(katViewModel) {
        katViewModel.katState.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.isVisible = state is ApiState.Loading
            if (state is ApiState.Success) handleSuccess(state.data)
            if (state is ApiState.Failure) handleFailure(state.msg)
        }
    }

    private fun handleSuccess(kats: List<Kat>) {
        (binding.rvKats.adapter as KatAdapter).updateList(kats)
    }

    private fun handleFailure(errorMsg: String) {
        Log.d(TAG, "onViewCreated: $errorMsg")
    }

    companion object {
        private const val TAG = "BrowseFragment"
    }
}