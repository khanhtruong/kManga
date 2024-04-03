package com.example.kmanga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kmanga.databinding.ItemHorizontalMangaBinding

class MangaHorizontalAdapter :
    RecyclerView.Adapter<MangaHorizontalAdapter.MangaHorizontalViewHolder>() {
    inner class MangaHorizontalViewHolder(
        private val binding: ItemHorizontalMangaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaHorizontalViewHolder {
        return MangaHorizontalViewHolder(
            ItemHorizontalMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MangaHorizontalViewHolder, position: Int) {
        holder.bindData()
    }
}