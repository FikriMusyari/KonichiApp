package com.fmt.konichi.Model

data class Anime(
    val malId: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val synopsis: String = "",
    val score: Double = 0.0,
    val type: String = "",
    val rank: Int = 0,
    val popularity: Int = 0,
    val episodes: Int = 0,
    val duration: String = "",
    val status: String = "",
    val aired: String = "",
    val studios: List<String> = emptyList(),
    val genres: List<String> = emptyList()
)
