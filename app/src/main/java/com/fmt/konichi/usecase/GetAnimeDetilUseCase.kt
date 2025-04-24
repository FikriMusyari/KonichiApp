package com.fmt.konichi.usecase

import com.fmt.konichi.Model.Anime
import com.fmt.konichi.data.repo.AnimeRepo

class GetAnimeDetilUseCase(
    private val repository: AnimeRepo
) {
    suspend operator fun invoke(id: Int): Anime? {
        return repository.getAnimeDetailById(id)
    }
}