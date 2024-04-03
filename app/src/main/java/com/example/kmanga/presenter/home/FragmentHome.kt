package com.example.kmanga.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kmanga.adapter.TopMangaAdapter
import com.example.kmanga.adapter.TopMangaContentAdapter
import com.example.kmanga.base.BaseFragment
import com.example.kmanga.databinding.FragmentHomeBinding
import com.example.kmanga.util.ConcatenableAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FragmentHome : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private lateinit var contentAdapter: ConcatAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var topMangaContentAdapter: TopMangaContentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridSpanSize = 2

        contentAdapter =
            ConcatAdapter(
                ConcatAdapter.Config.Builder()
                    .setIsolateViewTypes(false)
                    .build()
            )

        topMangaContentAdapter = TopMangaContentAdapter()
        val topMangasAdapter = TopMangaAdapter(0, gridSpanSize, topMangaContentAdapter)
        contentAdapter.addAdapter(topMangasAdapter)


        val layoutManager = GridLayoutManager(requireContext(), gridSpanSize)
        binding.recyclerViewContent.layoutManager = layoutManager
        binding.recyclerViewContent.adapter = contentAdapter

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val globalItemViewType = contentAdapter.getItemViewType(position)
                val spanSize: Int = contentAdapter
                    .adapters
                    .filterIsInstance<ConcatenableAdapter>()
                    .first {
                        it.hasGlobalViewItemType(globalItemViewType)
                    }
                    .spanSizeByType(globalItemViewType)
                return spanSize
            }
        }

        viewModel.topMangas.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                it.let{ topMangaContentAdapter.submitList(it) }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}