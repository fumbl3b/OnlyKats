package com.example.onlykats.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentBrowseBinding


class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)
        initViews()

    }

    private fun initViews() = with(binding) {
        detailButton.setOnClickListener {
            findNavController().navigate(R.id.action_browseFragment_to_detailFragment)
            Toast.makeText(context,"Details",Toast.LENGTH_SHORT).show()
        }
        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_browseFragment_to_settingsFragment)
            Toast.makeText(context,"Settings",Toast.LENGTH_SHORT).show()
        }
    }
}