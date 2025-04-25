package com.fmt.konichi.data.repo

import com.fmt.konichi.Model.Manga
import com.fmt.konichi.data.services.ApiService

class MangaRepoImpl(
    private val api: ApiService
) : MangaRepo {

    override suspend fun searchManga(query: String): List<Manga> {
        return api.searchManga(query).data.orEmpty().map { dto ->
            Manga(
                malId = dto.mal_id ?: 0,
                title = dto.title.orEmpty(),
                imageUrl = dto.images?.jpg?.image_url.orEmpty(),
                synopsis = dto.synopsis.orEmpty(),
                score = dto.score ?: 0.0,
                type = dto.type.orEmpty(),
                rank = dto.rank ?: 0,
                popularity = dto.popularity ?: 0,
                episodes = dto.episodes ?: 0,
                duration = dto.duration.orEmpty(),
                status = dto.status.orEmpty(),
                aired = dto.aired?.string.orEmpty(),
                studios = dto.studios?.mapNotNull { it.name } ?: emptyList(),
                genres = dto.genres?.mapNotNull { it.name } ?: emptyList()
            )
        }
    }

    override suspend fun getMangaDetailById(id: Int): Manga? {
        val dto = api.getMangaFullById(id).data ?: return null
        return Manga(
            malId = dto.mal_id ?: 0,
            title = dto.title.orEmpty(),
            imageUrl = dto.images?.jpg?.image_url.orEmpty(),
            synopsis = dto.synopsis.orEmpty(),
            score = dto.score ?: 0.0,
            type = dto.type.orEmpty(),
            rank = dto.rank ?: 0,
            popularity = dto.popularity ?: 0,
            episodes = dto.episodes ?: 0,
            duration = dto.duration.orEmpty(),
            status = dto.status.orEmpty(),
            aired = dto.aired?.string.orEmpty(),
            studios = dto.studios?.mapNotNull { it.name } ?: emptyList(),
            genres = dto.genres?.mapNotNull { it.name } ?: emptyList()
        )
    }


}