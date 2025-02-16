package com.mrinsaf.avitomusic

import android.os.Build
import android.os.Bundle
import android.Manifest
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrinsaf.core.ui.theme.AvitoMusicTheme
import com.mrinsaf.core.ui.viewModel.CoreViewModel
import com.mrinsaf.feature_api_tracks.data.ui.screens.ChartTracksScreen
import com.mrinsaf.feature_api_tracks.data.ui.viewModel.ChartTracksViewModel
import com.mrinsaf.feature_downloaded_tracks.ui.screens.DownloadedTracksScreen
import com.mrinsaf.feature_downloaded_tracks.ui.viewModel.DownloadedTacksViewModel
import com.mrinsaf.feature_player.ui.screens.MusicPlayerScreen
import com.mrinsaf.feature_player.ui.viewModel.MusicPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissions = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                println("ЗБС")
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions.launch(Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            requestPermissions.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        setContent {
            AvitoMusicTheme(dynamicColor = false) {
                MusicApp(
                    modifier = Modifier.imePadding(),
                    context = this
                )
            }
        }
    }
}


@Composable
fun MusicApp(modifier: Modifier, context: Context) {
    val navController = rememberNavController()
    val chartViewModel: ChartTracksViewModel = hiltViewModel()
    val tracksViewModel: DownloadedTacksViewModel = hiltViewModel()
    val coreViewModel: CoreViewModel = hiltViewModel()
    val musicPlayerViewModel: MusicPlayerViewModel = hiltViewModel()

    val isKeyboardOpen by keyboardVisibilityAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = "player",
                modifier = modifier.fillMaxSize()
            ) {
                composable("downloaded_tracks") {
                    DownloadedTracksScreen(
                        navController = navController,
                        downloadedTracksViewModel = tracksViewModel,
                        coreViewModel = coreViewModel
                    )
                }
                 composable("api_tracks") {
                     ChartTracksScreen(
                         navController,
                         chartViewModel = chartViewModel,
                         coreViewModel = coreViewModel,
                     )
                 }
                 composable("player") { MusicPlayerScreen(
                     viewModel = musicPlayerViewModel
                 ) }
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

@Composable
fun keyboardVisibilityAsState(): State<Boolean> {
    val density = LocalDensity.current
    val isKeyboardOpen = WindowInsets.ime.getBottom(density) > 0

    val keyboardState = remember { mutableStateOf(isKeyboardOpen) }

    LaunchedEffect(isKeyboardOpen) {
        snapshotFlow { isKeyboardOpen }
            .collect { open ->
                keyboardState.value = open
            }
    }
    return keyboardState
}

data class BottomNavItem(val icon: Painter, val route: String)

