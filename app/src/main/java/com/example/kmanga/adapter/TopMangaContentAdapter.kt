package com.example.kmanga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmanga.databinding.ItemHorizontalMangaBinding
import com.example.kmanga.domain.model.TopManga

class TopMangaCallback : DiffUtil.ItemCallback<TopManga>() {
    override fun areItemsTheSame(oldItem: TopManga, newItem: TopManga): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TopManga,
        newItem: TopManga
    ): Boolean {
        return oldItem == newItem
    }
}

class TopMangaContentAdapter() :
    ListAdapter<TopManga, TopMangaContentAdapter.TopMangaContentViewHolder>(TopMangaCallback()) {

    inner class TopMangaContentViewHolder(private val binding: ItemHorizontalMangaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: TopManga) {
            Glide.with(binding.root)
                .load(data.pictureURL)
                .centerCrop()
                .into(binding.imageViewMangaThumbnail)

            binding.lblMangaName.text = data.title
            binding.lblWatchedNumber.text = data.members
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopMangaContentAdapter.TopMangaContentViewHolder {
        return TopMangaContentViewHolder(
            ItemHorizontalMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: TopMangaContentAdapter.TopMangaContentViewHolder,
        position: Int
    ) {
        holder.bindData(this.getItem(position))
    }

}