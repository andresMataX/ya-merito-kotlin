package com.example.yameritoxmlcompose.ui.screens.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.yameritoxmlcompose.ui.navigation.AppScreens

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val lat: Double by mainViewModel.lat.observeAsState(initial = 0.0)

    Column {
        Text(text = "AAAAAA mi pichula1 ${lat.toString()}")
        Button(onClick = {
            navController.navigate(route = AppScreens.ConfirmScreen.route)
        }) {
            Text(text = "Siguiente")
        }
    }
}