package com.fmt.konichi.usecase

import com.fmt.konichi.Model.Anime
import com.fmt.konichi.data.repo.AnimeRepo

class SearchAnimeUseCase(
    private val repository: AnimeRepo
) {
    suspend operator fun invoke(query: String): List<Anime> {
        return repository.searchAnime(query)
    }
}