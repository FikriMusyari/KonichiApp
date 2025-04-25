package com.fmt.konichi.data.services

data class MangaSearchResponse(
    val data: List<MangaDto>? = null
)

data class MangaDto(
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
    val aired: AiredDto1? = null,
    val studios: List<StudioDto1>? = null,
    val genres: List<GenreDto1>? = null,
    val images: ImageWrapper1? = null
)

data class AiredDto1(
    val string: String? = null
)

data class StudioDto1(
    val name: String? = null
)

data class GenreDto1(
    val name: String? = null
)

data class ImageWrapper1(
    val jpg: JpgImage1? = null
)

data class JpgImage1(
    val image_url: String? = null,
    val small_image_url: String? = null,
    val large_image_url: String? = null
)
