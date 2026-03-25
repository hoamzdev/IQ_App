package com.hoamz.iq.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoamz.iq.R

//how to setup fontFamily
val TitleFontFamily = FontFamily(
    Font(R.font.js_semi_bold, FontWeight.Bold),
    Font(R.font.js_medium, FontWeight.Medium),
    Font(R.font.js_regular, FontWeight.Normal),
)

val ContentFontFamily = FontFamily(
    Font(R.font.mont_medium, FontWeight.Medium),
    Font(R.font.mont_regular, FontWeight.Normal),
    Font(R.font.mon_semi_bold, FontWeight.Bold),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ContentFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        color = Color.White,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ContentFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.White,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ContentFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        color = Color.White,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = ContentFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color.White,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = TitleFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 60.sp,
        color = Color.White,
        lineHeight = 30.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = TitleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.White,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ContentFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = Color.White,
        letterSpacing = 0.5.sp
    ),
)

val shape = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(16.dp),
    extraSmall = RoundedCornerShape(8.dp)
)