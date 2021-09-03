package com.example.onlykats.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
    }
}