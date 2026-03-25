package com.hoamz.iq.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hoamz.iq.ui.theme.BackgroundColor
import com.hoamz.iq.ui.theme.Pink40
import com.hoamz.iq.ui.theme.Pink80
import com.hoamz.iq.ui.theme.Purple40
import com.hoamz.iq.ui.theme.PurpleGrey40
import com.hoamz.iq.ui.theme.Typography
import com.hoamz.iq.ui.theme.onPrimaryColor
import com.hoamz.iq.ui.theme.onSecondaryColor
import com.hoamz.iq.ui.theme.shape

private val DarkColorScheme = darkColorScheme(
    primary = onPrimaryColor,
    secondary = PurpleGrey40,
    tertiary = Pink80,
    background = BackgroundColor,
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = onSecondaryColor,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40
)

@Composable
fun IQTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = shape
    )
}