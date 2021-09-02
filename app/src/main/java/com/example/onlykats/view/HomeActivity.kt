package com.example.onlykats.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlykats.R
import com.example.onlykats.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}