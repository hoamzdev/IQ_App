package com.hoamz.iq.presentation.navigation

/**
 * @author hwa..
 */
sealed class Page(val router : String) {
    object WelComeScreen : Page(router = "WelcomeScreenRouter")
    object PlayScreen : Page(router = "PlayScreenRouter?indexCurrentLevel={level}"){
        fun onNavToPlayScreen(level : Int) = "PlayScreenRouter?indexCurrentLevel=$level"
    }
    object LevelScreen : Page(router = "LevelScreenRouter")
}