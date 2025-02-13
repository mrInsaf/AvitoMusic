package com.mrinsaf.avitomusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrinsaf.feature_downloaded_tracks.ui.DownloadedTracksScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicApp()
        }
    }
}

@Composable
fun MusicApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = "downloaded_tracks",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("downloaded_tracks") { DownloadedTracksScreen(navController) }
                // composable("api_tracks") { ApiTracksScreen(navController) }
                // composable("player") { PlayerScreen(navController) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(painterResource(R.drawable.musical_note_unfilled), "api_tracks"),
        BottomNavItem(painterResource(R.drawable.diskette), "downloaded_tracks"),
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}

data class BottomNavItem(val icon: Painter, val route: String)

