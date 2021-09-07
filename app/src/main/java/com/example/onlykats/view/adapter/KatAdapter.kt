package com.example.onlykats.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlykats.databinding.KatItemBinding
import com.example.onlykats.model.Kat
import com.example.onlykats.util.loadWithGlide

class KatAdapter(
    private val katList: MutableList<Kat> = mutableListOf()
) : RecyclerView.Adapter<KatAdapter.KatViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = KatViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: KatViewHolder, position: Int) {
        holder.loadKat(katList[position])
    }

    override fun getItemCount(): Int = katList.size

    fun updateList(kats: List<Kat>) {
        katList.clear()
        katList.addAll(kats)
        notifyItemRangeChanged(0, katList.size)
        // notifyDataSetChanged()
    }

    class KatViewHolder(
        private val binding: KatItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadKat(kat: Kat) = with(binding) {
            ivKat.loadWithGlide(kat.url)
        }

        companion object {
            fun getInstance(parent: ViewGroup): KatViewHolder {
                val binding = KatItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return KatViewHolder(binding)
            }
        }
    }
}