package com.fmt.konichi.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmt.konichi.Model.Anime
import com.fmt.konichi.usecase.GetAnimeDetilUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetilViewModel @Inject constructor(
    private val getAnimeDetailUseCase: GetAnimeDetilUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)
    val anime: StateFlow<Anime?> = _anime

    init {
        val malIdString = savedStateHandle.get<String>("malId")
        val malId = malIdString?.toIntOrNull()
        if (malId != null) {
            viewModelScope.launch {
                _anime.value = getAnimeDetailUseCase(malId)
            }
        }
    }
}