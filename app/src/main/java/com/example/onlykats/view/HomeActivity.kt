package com.example.onlykats.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlykats.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    // by is a delegate lazy {}

    // private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var binding: ActivityMainBinding

    // val is a runtime constant
    // var is mutable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


}