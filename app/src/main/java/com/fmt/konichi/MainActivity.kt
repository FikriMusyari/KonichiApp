package com.fmt.konichi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fmt.konichi.viewmodel.AuthViewModel
import com.fmt.konichi.screen.AnimeDetilScreen
import com.fmt.konichi.screen.AnimeScreen
import com.fmt.konichi.screen.LoginScreen
import com.fmt.konichi.viewmodel.AnimeDetilViewModel
import com.fmt.konichi.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.fmt.konichi.screen.MangaDetilScreen
import com.fmt.konichi.screen.MangaScreen
import com.fmt.konichi.screen.SettingScreen
import com.fmt.konichi.screen.SignupScreen
import com.fmt.konichi.ui.theme.KonichiTheme
import com.fmt.konichi.viewmodel.MangaDetilViewModel
import com.fmt.konichi.viewmodel.MangaViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            KonichiTheme {
                NavHost(navController = navController, startDestination = Screen.Login.route) {

                    composable(Screen.Login.route) {
                        val viewModel: AuthViewModel = hiltViewModel()
                        LoginScreen(viewModel, navController)
                    }
                    composable(Screen.Register.route) {
                        val viewModel: AuthViewModel = hiltViewModel()
                        SignupScreen(viewModel, navController)
                    }
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
                        MangaScreen(viewModel, navController)
                    }
                    composable(Screen.MangaDetail.route) {
                        val viewModel: MangaDetilViewModel = hiltViewModel()
                        MangaDetilScreen(viewModel)
                    }
                    composable(Screen.Setting.route) {
                        val viewModel: AuthViewModel = hiltViewModel()
                        SettingScreen(viewModel, navController)
                    }
                }
            }

        }
    }
}
