package com.fmt.konichi.data.repo

import com.fmt.konichi.Model.Anime

interface AnimeRepo {
    suspend fun searchAnime(query: String): List<Anime>
    suspend fun getAnimeDetailById(id: Int): Anime?
}