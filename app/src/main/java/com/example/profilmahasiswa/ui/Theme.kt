package com.example.profilmahasiswa.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = lightColorScheme(
    primary = Color(0xFF174E91),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7E6FF),
    onPrimaryContainer = Color(0xFF0B2D5A),
    secondary = Color(0xFF0C827A),
    background = Color(0xFFF3F6FB),
    surface = Color.White,
    onSurface = Color(0xFF1A2638),
    error = Color(0xFFB3261E)
)

@Composable
fun ProfileAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = AppColors, content = content)
}
