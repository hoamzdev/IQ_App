package com.hoamz.iq.presentation.navigation

import androidx.navigation.NavHostController

/**
 * @author hwa..
 */

class MainRouter(
    val navHostController : NavHostController
) {
    //cac ham dieu huong viet o dayt
    fun onBackPress(){
        navHostController.popBackStack()
    }

    fun onNavToPlayScreen(){
        navHostController.navigate(route = Page.PlayScreen.router){
            launchSingleTop = true
            restoreState = true
        }
    }

    fun onBackToWelcomeScreen(){
        navHostController.navigate(route = Page.WelComeScreen.router){
            popUpTo(navHostController.graph.id)
            launchSingleTop = true
            restoreState = true
        }
    }

    fun onNavToLevelScreen(){
        navHostController.navigate(route = Page.LevelScreen.router){
            launchSingleTop = true
            restoreState = true
        }
    }

}