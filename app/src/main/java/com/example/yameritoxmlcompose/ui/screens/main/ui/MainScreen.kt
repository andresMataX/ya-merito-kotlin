package com.example.yameritoxmlcompose.ui.screens.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.yameritoxmlcompose.ui.navigation.AppScreens
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val lat: String by mainViewModel.lat.observeAsState(initial = "")

    Column {
        Text(text = "MainScreen")
        Button(onClick = {
            navController.navigate(route = AppScreens.ConfirmScreen.route)
        }) {
            Text(text = "Siguiente")
        }
    }
}