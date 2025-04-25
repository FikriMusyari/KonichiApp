package com.fmt.konichi.usecase

import com.fmt.konichi.Model.Manga
import com.fmt.konichi.data.repo.MangaRepo

class GetMangaDetilUseCase(
    private val repository: MangaRepo
) {
    suspend operator fun invoke(id: Int): Manga? {
        return repository.getMangaDetailById(id)
    }
}