package com.example.yameritoxmlcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yameritoxmlcompose.ui.fragments.LocalizationViewModel
import com.example.yameritoxmlcompose.ui.screens.confirm.ui.ConfirmScreen
import com.example.yameritoxmlcompose.ui.screens.confirm.ui.ConfirmViewModel
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainScreen
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainViewModel
import com.example.yameritoxmlcompose.ui.screens.success.ui.SuccessScreen
import com.example.yameritoxmlcompose.ui.screens.travel.ui.TravelScreen

@Composable
fun AppNavigation(localizationViewModel: LocalizationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController, mainViewModel = MainViewModel(), localizationViewModel)
        }
        composable(
            route = AppScreens.ConfirmScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            ConfirmScreen(
                navController,
                confirmViewModel = ConfirmViewModel(),
                it.arguments?.getString("text"),
                localizationViewModel
            )
        }
        composable(
            route = AppScreens.TravelScreen.route + "/{destino}/{rango}",
            arguments = listOf(
                navArgument(name = "destino") {
                    type = NavType.StringType
                },
                navArgument(name = "rango") {
                    type = NavType.StringType
                },
            )
        ) {
            TravelScreen(
                navController,
                it.arguments?.getString("destino"),
                it.arguments?.getString("rango"),
                localizationViewModel,
                confirmViewModel = ConfirmViewModel(),
            )
        }
        composable(route = AppScreens.SuccessScreen.route) {
            SuccessScreen(navController = navController)
        }
    }
}