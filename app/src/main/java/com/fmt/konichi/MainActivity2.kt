package com.fmt.konichi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fmt.konichi.screen.AnimeDetilScreen
import com.fmt.konichi.screen.AnimeScreen
import com.fmt.konichi.viewmodel.AnimeDetilViewModel
import com.fmt.konichi.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.fmt.konichi.screen.MangaDetilScreen
import com.fmt.konichi.screen.MangaScreen
import com.fmt.konichi.viewmodel.MangaDetilViewModel
import com.fmt.konichi.viewmodel.MangaViewModel


@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Ambil startDestination dari Intent, default ke AnimeList
        val start = intent.getStringExtra("startDestination") ?: Screen.AnimeList.route

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = start) {
                composable(Screen.AnimeList.route) {
                    val viewModel: AnimeViewModel = hiltViewModel()
                    AnimeScreen(viewModel, navController)
                }
                composable(Screen.AnimeDetail.route) {
                    val viewModel: AnimeDetilViewModel = hiltViewModel()
                    AnimeDetilScreen(viewModel)
                }
                composable(Screen.MangaList.route) {
                    val viewModel: MangaViewModel = hiltViewModel()
//                    MangaScreen(viewModel, navController)
                }
                composable(Screen.MangaDetail.route) {
                    val viewModel: MangaDetilViewModel = hiltViewModel()
                    MangaDetilScreen(viewModel)
                }
            }
        }
    }
}
