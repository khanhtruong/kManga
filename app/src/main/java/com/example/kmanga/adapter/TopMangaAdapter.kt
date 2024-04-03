package com.example.kmanga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kmanga.databinding.ItemHorizontalMangaBinding
import com.example.kmanga.databinding.ItemTopMangasBinding
import com.example.kmanga.domain.model.TopManga
import com.example.kmanga.util.ConcatenableAdapter

class TopMangaAdapter(
    override val concatAdapterIndex: Int,
    private val totalSpanSize: Int,
    private val adapter: TopMangaContentAdapter
) : RecyclerView.Adapter<TopMangaAdapter.TopMangaViewHolder>(),
    ConcatenableAdapter {

    inner class TopMangaViewHolder(private val binding: ItemTopMangasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData() {
            binding.recyclerViewTopMangas.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewTopMangas.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMangaViewHolder {
        return TopMangaViewHolder(
            ItemTopMangasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopMangaViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount() = 1

    override fun getItemViewType(position: Int): Int {
        return globalViewItemType()
    }

    override fun spanSizeByType(globalItemViewType: Int): Int {
        return totalSpanSize
    }
}
