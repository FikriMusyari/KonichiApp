package com.fmt.konichi.data.repo

import com.fmt.konichi.Model.Manga

interface MangaRepo {
    suspend fun searchManga(query: String): List<Manga>
    suspend fun getMangaDetailById(id: Int): Manga?
}