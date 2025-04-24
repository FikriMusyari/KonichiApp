package com.fmt.konichi

sealed class Screen(val route: String) {
    data object AnimeList : Screen("anime_list")
    data object AnimeDetail : Screen("anime_detail/{malId}") {
        fun createRoute(malId: Int) = "anime_detail/$malId"
    }
}