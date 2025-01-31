package ru.amalkoott.findjob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Black,

    secondary = Blue,
    secondaryContainer = DarkBlue,
    tertiary = Green,
    tertiaryContainer = DarkGreen,

    surface = Grey1,
    surfaceVariant = Grey2,

    background = Black,

    onSurface = White,
    onSurfaceVariant = Grey3,
    onBackground = White,
    onSecondary = Grey4,
    onTertiary = White,
    onTertiaryContainer = White,


)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    primaryContainer = Purple700,
    secondary = Teal200,
)

@Composable
fun FindJobTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}