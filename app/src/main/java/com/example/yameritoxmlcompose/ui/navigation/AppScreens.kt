package com.example.yameritoxmlcompose.ui.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("main_screen")
    object ConfirmScreen : AppScreens("confirm_screen")
    object SuccessScreen : AppScreens("success_screen")
}