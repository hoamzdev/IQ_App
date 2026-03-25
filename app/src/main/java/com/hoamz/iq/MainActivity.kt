package com.hoamz.iq

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.hoamz.iq.common.LocalScreenSize
import com.hoamz.iq.common.ScreenSize
import com.hoamz.iq.common.findActivity
import com.hoamz.iq.presentation.navigation.MainGraph
import com.hoamz.iq.presentation.ui.theme.IQTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val configuration = LocalConfiguration.current
            val context = LocalContext.current
            val screenSize = remember(configuration) {
                ScreenSize(
                    width = configuration.screenWidthDp.dp,
                    height = configuration.screenHeightDp.dp
                )
            }
            val activity = context.findActivity()
            LaunchedEffect(Unit) {
                activity?.window?.setUpSystemDevice()
            }
            val navHostController = rememberNavController()
            CompositionLocalProvider(LocalScreenSize provides screenSize) {
                IQTheme {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.onSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        MainGraph(
                            navHostController = navHostController
                        )
                    }
                }
            }
        }
    }
}

fun Window.setUpSystemDevice(){
    WindowCompat.setDecorFitsSystemWindows(this,false)
    WindowInsetsControllerCompat(this,this.decorView.findViewById(android.R.id.content))
        .let {
            controllerCompat ->
            controllerCompat.hide(WindowInsetsCompat.Type.systemBars())
            controllerCompat.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
}
