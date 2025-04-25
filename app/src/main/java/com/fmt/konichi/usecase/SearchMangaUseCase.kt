package com.fmt.konichi.usecase

import com.fmt.konichi.Model.Manga
import com.fmt.konichi.data.repo.MangaRepo

class SearchMangaUseCase(
    private val repository: MangaRepo
) {
    suspend operator fun invoke(query: String): List<Manga> {
        return repository.searchManga(query)
    }
}