package com.fmt.konichi.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.fmt.konichi.viewmodel.AnimeDetilViewModel

@Composable
fun AnimeDetilScreen(viewModel: AnimeDetilViewModel) {
    val anime by viewModel.anime.collectAsStateWithLifecycle()

    if (anime != null) {
        val data = anime!!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                model = data.imageUrl,
                contentDescription = data.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Score: ${data.score}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Type: ${data.type}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Episodes: ${data.episodes} • Duration: ${data.duration}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Status: ${data.status}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Aired: ${data.aired}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Rank: #${data.rank} • Popularity: #${data.popularity}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (data.studios.isNotEmpty()) {
                Text(
                    text = "Studios: ${data.studios.joinToString()}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (data.genres.isNotEmpty()) {
                Text(
                    text = "Genres: ${data.genres.joinToString()}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = data.synopsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            CircularProgressIndicator()
        }
    }
}