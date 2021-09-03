package com.example.onlykats.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlykats.databinding.KatItemBinding
import com.example.onlykats.model.Kat
import com.example.onlykats.util.loadWithGlide
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class KatAdapter(val katList: List<Kat>) : RecyclerView.Adapter<KatAdapter.KatViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KatViewHolder {
        val katItemBinding: KatItemBinding =
            KatItemBinding.inflate(LayoutInflater.from(parent.context))

        return KatViewHolder(katItemBinding)
    }

    override fun onBindViewHolder(holder: KatViewHolder, position: Int) {
        holder.bind(katList[position])
    }

    override fun getItemCount(): Int = katList.size


    class KatViewHolder(val binding: KatItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(katItem: Kat) {
            // do binding stuff
            with(binding) {
                val katUrl = katItem.url ?: "" // TODO: put default value here
                katImage.loadWithGlide(katUrl)
            }
        }
    }
}