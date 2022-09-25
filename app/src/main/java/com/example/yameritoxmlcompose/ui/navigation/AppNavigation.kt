package com.example.yameritoxmlcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yameritoxmlcompose.ui.screens.confirm.ui.ConfirmScreen
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            route = AppScreens.ConfirmScreen.route,
        ) {
            ConfirmScreen(navController)
        }
    }
}