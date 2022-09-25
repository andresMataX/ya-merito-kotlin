package com.example.yameritoxmlcompose.ui.screens.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.yameritoxmlcompose.ui.navigation.AppScreens

@Composable
fun MainScreen(navController: NavHostController) {
    Column {
        Text(text = "AAAAAA mi pichula1")
        Button(onClick = {
            navController.navigate(route = AppScreens.ConfirmScreen.route)
        }) {
            Text(text = "Siguiente")
        }
    }
}