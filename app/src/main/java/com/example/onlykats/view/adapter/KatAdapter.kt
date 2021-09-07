package com.example.onlykats.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.onlykats.databinding.KatItemBinding
import com.example.onlykats.model.Kat
import com.example.onlykats.util.loadWithGlide

class KatAdapter : RecyclerView.Adapter<KatAdapter.KatViewHolder>() {


    class KatViewHolder(
        private val binding: KatItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadKat(kat: Kat) = with(binding) {
            ivKat.loadWithGlide(kat.url)
        }
    }
}