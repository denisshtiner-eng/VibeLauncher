package com.vibe.launcher.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vibe.launcher.viewmodel.LauncherViewModel

@Composable
fun LauncherRoot(viewModel: LauncherViewModel) {
    val apps by viewModel.apps.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var drawerOpen by remember { mutableStateOf(false) }

    // Обработка кнопки "Назад"
    BackHandler(enabled = drawerOpen) {
        drawerOpen = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Home screen всегда виден
        HomeScreen(
            onOpenDrawer = { drawerOpen = true }
        )

        // App Drawer с плавной анимацией
        AnimatedVisibility(
            visible = drawerOpen,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 320)
            ) + fadeIn(animationSpec = tween(280)),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 280)
            ) + fadeOut(animationSpec = tween(220))
        ) {
            AppDrawer(
                apps = apps,
                isLoading = isLoading,
                onAppClick = { app ->
                    viewModel.launchApp(app)
                    drawerOpen = false
                },
                onClose = { drawerOpen = false }
            )
        }
    }
}
