package com.fmt.konichi

sealed class BottomNavItem(val route: String, val label: String) {
    object Anime : BottomNavItem(Screen.AnimeList.route, "Anime")
    object Manga : BottomNavItem(Screen.MangaList.route, "Manga")
    object Settings : BottomNavItem("settings", "Settings") // belum diimplementasi
}