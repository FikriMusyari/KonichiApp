package com.fmt.konichi.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.fmt.konichi.Model.Manga
import com.fmt.konichi.Screen
import com.fmt.konichi.viewmodel.MangaViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.fmt.konichi.components.BottomNavigationBar
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun MangaScreen(viewModel: MangaViewModel, navController: NavController) {
    var query by remember { mutableStateOf("") }
    val MangaList by viewModel.MangaList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Scaffold(
        containerColor = Color(0xFFF2F2F2),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        delay(500L)
                        viewModel.searchManga(query)
                    }
                },
                label = {
                    Text("Search Manga", color = Color.Black)
                },
                singleLine = true,
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = {
                            query = ""
                            viewModel.searchManga("")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = Color.Black
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(vertical = 2.dp),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFD7D6D7),
                    unfocusedContainerColor = Color(0xFFD7D6D7),
                    focusedBorderColor = Color(0xFF047857),
                    unfocusedBorderColor = Color(0xFF047857),
                    cursorColor = Color(0xFF047857)
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF047857))
                    }
                }

                MangaList.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No manga found", color = Color.Black)
                    }
                }

                else -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        items(MangaList.size) { i ->
                            MangaItem(Manga = MangaList[i], navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MangaItem(Manga: Manga, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate(Screen.MangaDetail.createRoute(Manga.malId))
            },
        color = Color.White,
        shadowElevation = 4.dp,
        border = BorderStroke(
            1.dp,
            Color(0xFF047857)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(Manga.imageUrl),
                contentDescription = Manga.title,
                modifier = Modifier
                    .size(width = 100.dp, height = 140.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = Manga.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "⭐ ${Manga.score}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF047857)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Year: ${Manga.aired}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = Manga.synopsis.take(100) + "...",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 3
                )
            }
        }
    }
}
