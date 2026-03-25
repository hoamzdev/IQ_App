package com.hoamz.iq.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoamz.iq.presentation.ui.screen.level.LevelScreen
import com.hoamz.iq.presentation.ui.screen.play.PlayScreen
import com.hoamz.iq.presentation.ui.screen.play.PlayViewModel
import com.hoamz.iq.presentation.ui.screen.welcome.WelcomeScreen
import com.hoamz.iq.presentation.ui.screen.welcome.WelcomeViewModel
import com.hoamz.iq.presentation.ui.screen.level.LevelViewModel
import kotlinx.coroutines.delay

/**
 * @author hwa..
 */

@Composable
fun MainGraph(
    navHostController: NavHostController
) {
    val mainRouter = remember { MainRouter(navHostController) }
    NavHost(
        navController = navHostController,
        startDestination = Page.WelComeScreen.router
    ) {
        composable(route = Page.WelComeScreen.router) {
            val welcomeViewModel : WelcomeViewModel = hiltViewModel()
            WelcomeScreen(
                welcomeViewModel = welcomeViewModel,
                mainRouter = mainRouter
            )
        }
        composable(route = Page.PlayScreen.router) {
            val playViewModel : PlayViewModel = hiltViewModel()
            PlayScreen(
                mainRouter = mainRouter,
                playViewModel = playViewModel
            )
        }
        composable(route = Page.LevelScreen.router) {
            val levelViewModel : LevelViewModel = hiltViewModel()
            LevelScreen(
                mainRouter = mainRouter,
                levelViewModel = levelViewModel
            )
        }
    }
}