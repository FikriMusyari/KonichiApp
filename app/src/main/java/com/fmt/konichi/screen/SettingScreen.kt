package com.fmt.konichi.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.transform.CircleCropTransformation
import coil3.request.transformations
import com.fmt.konichi.R
import com.fmt.konichi.Screen
import com.fmt.konichi.components.BottomNavBar
import com.fmt.konichi.viewmodel.AuthViewModel

@Composable
fun SettingScreen(viewModel: AuthViewModel, navController: NavController) {
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    var showLogoutDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.ShowUserName()
    }

    Scaffold(
        containerColor = Color(0xFFF2F2F2),
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Card: User Info + Logout
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(null)
                            .transformations(CircleCropTransformation())
                            .build(),
                        placeholder = rememberVectorPainter(image = Icons.Default.PersonOutline),
                        error = rememberVectorPainter(image = Icons.Default.PersonOutline),
                        contentDescription = null,
                        modifier = Modifier
                            .sizeIn(48.dp, 48.dp, 74.dp, 74.dp)
                            .padding(end = 8.dp)
                    )

                    Text(
                        text = currentUser?.userName ?: "",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout"
                        )
                    }
                }
            }

            // 🔔 AlertDialog for logout confirmation
            if (showLogoutDialog) {
                AlertDialog(
                    onDismissRequest = { showLogoutDialog = false },
                    title = { Text(text = "Logout") },
                    text = { Text("Are you sure you want to logout?") },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.logOut()
                            showLogoutDialog = false
                            navController.navigate(Screen.Login.route) {
                                popUpTo(0)
                            }
                        }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showLogoutDialog = false }) {
                            Text("No")
                        }
                    }
                )
            }

            // Card: Tentang Aplikasi
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.mipmap.ic_launcher)
                            .build(),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 8.dp)
                    )

                    Text(
                        text = "AnimeApp",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Versi 1.0.0",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Aplikasi untuk eksplorasi Anime dan Manga favoritmu, lengkap dengan fitur pencarian, daftar tontonan, dan informasi mendalam.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Dikembangkan oleh:",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/FikriMusyari"))
                                context.startActivity(intent)
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        AsyncImage(
                            model = "https://github.com/FikriMusyari.png",
                            contentDescription = "Github Icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Fikri Taufiq",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF1E88E5),
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mhmdtaqi"))
                                context.startActivity(intent)
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        AsyncImage(
                            model = "https://github.com/mhmdtaqi.png",
                            contentDescription = "Github Icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Muhammad Taqi",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF1E88E5),
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "© 2025 Fikri & Taqi. All rights reserved.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
