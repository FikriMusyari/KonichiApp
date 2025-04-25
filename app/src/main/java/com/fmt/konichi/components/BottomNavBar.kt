package com.fmt.konichi.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fmt.konichi.BottomNavItem

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Anime to Icons.Default.List,
        BottomNavItem.Manga to Icons.Default.Book,
        BottomNavItem.Settings to Icons.Default.Settings,
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color.White, // Warna latar navbar
        contentColor = Color.Black    // Warna ikon & label default
    ) {
        items.forEach { (item, icon) ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = item.label,
                        tint = if (isSelected) Color(0xFF047857) else Color.Black
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (isSelected) Color(0xFF047857) else Color.Black
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0x22047857) // transparan hijau saat aktif
                )
            )
        }
    }
}
