package com.fmt.konichi

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("signup")
    data object Setting : Screen("settings")
    data object AnimeList : Screen("anime_list")
    data object AnimeDetail : Screen("anime_detail/{malId}") {
        fun createRoute(malId: Int) = "anime_detail/$malId"
    }

    data object MangaList : Screen("manga_list")
    data object MangaDetail : Screen("manga_detail/{malId}") {
        fun createRoute(malId: Int) = "manga_detail/$malId"
    }

}
