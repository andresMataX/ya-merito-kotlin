package com.example.yameritoxmlcompose.ui.screens.confirm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.yameritoxmlcompose.ui.navigation.AppScreens

@Composable
fun ConfirmScreen(navController: NavHostController) {
    Column {
        Text(text = "ooooo")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Atrás")
        }
    }
}