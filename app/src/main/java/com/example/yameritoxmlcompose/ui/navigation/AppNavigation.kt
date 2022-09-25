package com.example.yameritoxmlcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yameritoxmlcompose.ui.screens.confirm.ui.ConfirmScreen
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainScreen
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController, mainViewModel = MainViewModel())
        }
        composable(
            route = AppScreens.ConfirmScreen.route,
        ) {
            ConfirmScreen(navController)
        }
    }
}