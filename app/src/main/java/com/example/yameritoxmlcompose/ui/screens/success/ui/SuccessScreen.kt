package com.example.yameritoxmlcompose.ui.screens.success.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun SuccessScreen(navController: NavHostController) {

    Texto()
}

@Preview()
@Composable
fun Texto() {
    Text(text = "Hola Mundo ooooo")
}