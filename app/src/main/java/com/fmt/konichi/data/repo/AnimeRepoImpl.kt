package com.fmt.konichi.data.repo

import com.fmt.konichi.Model.Anime
import com.fmt.konichi.data.services.ApiService

class AnimeRepoImpl(
    private val api: ApiService
) : AnimeRepo {

    override suspend fun searchAnime(query: String): List<Anime> {
        return api.searchAnime(query).data.orEmpty().map { dto ->
            Anime(
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

    override suspend fun getAnimeDetailById(id: Int): Anime? {
        val dto = api.getAnimeFullById(id).data ?: return null
        return Anime(
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