package com.fmt.konichi.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmt.konichi.Model.Manga
import com.fmt.konichi.usecase.GetMangaDetilUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaDetilViewModel @Inject constructor(
    private val getMangaDetailUseCase: GetMangaDetilUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _Manga = MutableStateFlow<Manga?>(null)
    val Manga: StateFlow<Manga?> = _Manga

    init {
        val malIdString = savedStateHandle.get<String>("malId")
        val malId = malIdString?.toIntOrNull()
        if (malId != null) {
            viewModelScope.launch {
                _Manga.value = getMangaDetailUseCase(malId)
            }
        }
    }
}