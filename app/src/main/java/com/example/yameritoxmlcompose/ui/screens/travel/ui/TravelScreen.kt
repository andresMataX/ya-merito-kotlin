package com.example.yameritoxmlcompose.ui.screens.travel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yameritoxmlcompose.R
import com.example.yameritoxmlcompose.ui.fragments.LocalizationViewModel

@Composable
fun TravelScreen(
    navController: NavController,
    text: String?,
    localizationViewModel: LocalizationViewModel
) {

    val maliFamiliy = FontFamily(
        Font(R.font.mali_bold, FontWeight.Bold),
        Font(R.font.mali_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.mali_extralight, FontWeight.ExtraLight),
        Font(R.font.mali_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
        Font(R.font.mali_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.mali_light, FontWeight.Light),
        Font(R.font.mali_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.mali_medium, FontWeight.Medium),
        Font(R.font.mali_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.mali_regular, FontWeight.Normal),
        Font(R.font.mali_semibold, FontWeight.SemiBold),
        Font(R.font.mali_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    )

    // Contenedor principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 40.dp, end = 40.dp)
            .background(color = Color.White)
    ) {
        Travel(navController, maliFamiliy, text, localizationViewModel)
    }
}

@Composable
fun Travel(
    navController: NavController,
    maliFamiliy: FontFamily,
    text: String?,
    localizationViewModel: LocalizationViewModel
) {
    Header(maliFamiliy)
}

@Composable
fun Header(maliFamiliy: FontFamily) {
    Text(
        text = "Viaje iniciado",
        fontSize = 40.sp,
        fontFamily = maliFamiliy,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth(),

    )
}
