package com.hoamz.iq.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author hwa..
 */

@Composable
fun Modifier.noRippleClick(
    onClick :() -> Unit
) : Modifier{
    return this.clickable(
        indication = null,
        interactionSource = remember {MutableInteractionSource() }
    ) {
        onClick()
    }
}

@Composable
fun SetHeight(value : Dp){
    Spacer(modifier = Modifier.height(value))
}

@Composable
fun SetWidth(value : Dp){
    Spacer(modifier = Modifier.width(value))
}

data class ScreenSize(
    val height : Dp = 0.dp,
    val width : Dp = 0.dp
)

val LocalScreenSize = compositionLocalOf { ScreenSize() }