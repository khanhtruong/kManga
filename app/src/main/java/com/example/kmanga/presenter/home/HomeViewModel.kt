package com.example.kmanga.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmanga.domain.model.TopManga
import com.example.kmanga.domain.usecase.top_manga.TopMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topMangaUseCase: TopMangaUseCase
) : ViewModel() {
    private val _topMangas: MutableStateFlow<List<TopManga>> = MutableStateFlow(emptyList())
    val topMangas = _topMangas.asStateFlow()


    init {
        viewModelScope.launch {
            topMangaUseCase.getTopManga("all")
                .collectLatest {
                    _topMangas.value = it
                }
        }
    }
}