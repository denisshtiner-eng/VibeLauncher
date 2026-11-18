package com.vibe.launcher.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vibe.launcher.ui.theme.VibeCyan
import com.vibe.launcher.ui.theme.VibePurple
import com.vibe.launcher.ui.theme.VibeTextPrimary
import com.vibe.launcher.ui.theme.VibeTextSecondary
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onOpenDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    // Обновляем часы каждую секунду
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            currentDate = LocalDate.now()
            delay(1000)
        }
    }

    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }
    val dateFormatter = remember {
        DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale("ru"))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    // Свайп вверх открывает drawer
                    if (dragAmount < -12f) {
                        onOpenDrawer()
                    }
                }
            }
    ) {
        // Большие красивые часы
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentTime.format(timeFormatter),
                fontSize = 86.sp,
                fontWeight = FontWeight.Thin,
                color = VibeTextPrimary,
                letterSpacing = (-2).sp
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = currentDate.format(dateFormatter)
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("ru")) else it.toString() },
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = VibeTextSecondary
            )
        }

        // Подсказка внизу
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Красивая линия-индикатор
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(VibePurple, VibeCyan)
                        )
                    )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "свайп вверх",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = VibeTextSecondary.copy(alpha = 0.7f),
                letterSpacing = 1.sp
            )
        }
    }
}
