package com.example.onlykats.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentBrowseBinding
import com.example.onlykats.model.Kat
import com.example.onlykats.view.adapter.KatAdapter
import com.example.onlykats.viewModel.KatViewModel


class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    private val katViewModel by viewModels<KatViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)
        initViews()
    }

    private fun initViews() = with(binding) {

//        detailButton.setOnClickListener {
//            findNavController().navigate(R.id.action_browseFragment_to_detailFragment)
//            Toast.makeText(context,"Details",Toast.LENGTH_SHORT).show()
//        }
//        settingsButton.setOnClickListener {
//            findNavController().navigate(R.id.action_browseFragment_to_settingsFragment)
//            Toast.makeText(context,"Settings",Toast.LENGTH_SHORT).show()
//        }
        // RecyclerView
        rvKats.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvKats.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}