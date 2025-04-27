package com.fmt.konichi.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.fmt.konichi.viewmodel.MangaDetilViewModel

@Composable
fun MangaDetilScreen(viewModel: MangaDetilViewModel) {
    val Manga by viewModel.Manga.collectAsStateWithLifecycle()

    if (Manga != null) {
        val data = Manga!!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF2F2F2))
        ) {
            AsyncImage(
                model = data.imageUrl,
                contentDescription = data.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = data.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoRow("⭐ Score", "${data.score}", Color(0xFF047857))
                    InfoRow("Type", data.type)
                    InfoRow("Episodes", data.episodes.toString())
                    InfoRow("Duration", data.duration)
                    InfoRow("Status", data.status)
                    InfoRow("Aired", data.aired)
                    InfoRow("Rank", "#${data.rank}")
                    InfoRow("Popularity", "#${data.popularity}")

                    if (data.studios.isNotEmpty()) {
                        InfoRow("Studios", data.studios.joinToString())
                    }

                    if (data.genres.isNotEmpty()) {
                        InfoRow("Genres", data.genres.joinToString())
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF047857),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Text(
                text = data.synopsis,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = Color(0xFF047857))
        }
    }
}
