package com.fmt.konichi.data.services

data class AnimeSearchResponse(
    val data: List<AnimeDto>? = null
)

data class AnimeDto(
    val mal_id: Int? = null,
    val title: String? = null,
    val synopsis: String? = null,
    val score: Double? = null,
    val type: String? = null,
    val rank: Int? = null,
    val popularity: Int? = null,
    val episodes: Int? = null,
    val duration: String? = null,
    val status: String? = null,
    val aired: AiredDto? = null,
    val studios: List<StudioDto>? = null,
    val genres: List<GenreDto>? = null,
    val images: ImageWrapper? = null
)

data class AiredDto(
    val string: String? = null
)

data class StudioDto(
    val name: String? = null
)

data class GenreDto(
    val name: String? = null
)

data class ImageWrapper(
    val jpg: JpgImage? = null
)

data class JpgImage(
    val image_url: String? = null,
    val small_image_url: String? = null,
    val large_image_url: String? = null
)
