package com.fmt.konichi.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.fmt.konichi.Screen
import com.fmt.konichi.viewmodel.AuthViewModel
import com.fmt.konichi.components.BottomNavBar

@Composable
fun SettingScreen(viewModel: AuthViewModel, navController: NavController )
     {
         val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
         var showLogoutDialog by remember { mutableStateOf(false) }

         LaunchedEffect(Unit) {
             viewModel.ShowUserName() // trigger untuk ambil data user dari Firebase
         }


         Scaffold(
             containerColor = Color(0xFFF2F2F2),
             bottomBar = { BottomNavBar(navController) }
         ) { innerPadding ->
             Box(
                 modifier = Modifier
                     .padding(innerPadding)
                     .fillMaxSize()
             )

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
                     title = {
                         Text(text = "Logout")
                     },
                     text = {
                         Text("Are you sure you want to logout?")
                     },
                     confirmButton = {
                         TextButton(onClick = {
                             viewModel.logOut()
                             showLogoutDialog = false
                             navController.navigate(Screen.Login.route) {
                                 popUpTo(0) // Clear all backstack
                             }
                         }) {
                             Text("Yes")
                         }
                     },
                     dismissButton = {
                         TextButton(onClick = {
                             showLogoutDialog = false
                         }) {
                             Text("No")
                         }
                     }
                 )
             }
         }
}

