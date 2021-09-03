package com.example.onlykats.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.onlykats.R
import com.example.onlykats.databinding.FragmentBrowseBinding


class BrowseFragment : Fragment(R.layout.fragment_browse) {

    private lateinit var binding: FragmentBrowseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrowseBinding.bind(view)


    }

    fun testMethod(num: Int): Int {
        return num + 1
    }
}