package com.fmt.konichi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmt.konichi.Model.Manga
import com.fmt.konichi.usecase.SearchMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val searchMangaUseCase: SearchMangaUseCase
) : ViewModel() {

    private val _MangaList = MutableStateFlow<List<Manga>>(emptyList())
    val MangaList: StateFlow<List<Manga>> = _MangaList

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun searchManga(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _MangaList.value = searchMangaUseCase(query)
            _isLoading.value = false
        }
    }
}