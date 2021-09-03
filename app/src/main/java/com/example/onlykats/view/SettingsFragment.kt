package com.example.onlykats.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }
}