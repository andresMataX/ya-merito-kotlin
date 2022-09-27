package com.example.yameritoxmlcompose.ui.screens.travel.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    Column {
        Header(maliFamiliy)
        Spacer(modifier = Modifier.height(104.dp))
        IconoBus()
        EstatusViaje(maliFamiliy, text)
        Spacer(modifier = Modifier.height(48.dp))
        IconoCancelar(navController)
    }
}

@Composable
fun IconoCancelar(navController: NavController) {

    TextButton(
        onClick = { navController.popBackStack() },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cancelar),
            contentDescription = "Cancelar",
            tint = Color.Black,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )
    }
}

@Composable
fun EstatusViaje(maliFamiliy: FontFamily, text: String?) {

    Text(
        text = "Destino",
        fontSize = 20.sp,
        fontFamily = maliFamiliy,
        color = Color.Black
    )
    Text(
        text = text!!,
        fontSize = 32.sp,
        fontFamily = maliFamiliy,
        color = Color.Black,
        fontWeight = FontWeight.Light
    )
    Text(
        // TODO: Cambiar por la distancia
        text = "Distancia con el destino: 6.2km",
        fontSize = 16.sp,
        fontFamily = maliFamiliy,
        color = Color.Black,
    )
    Text(
        // TODO: Cambiar por el rango
        text = "Rango seleccionado: 1.5km",
        fontSize = 16.sp,
        fontFamily = maliFamiliy,
        color = Color.Black,
        fontWeight = FontWeight.ExtraLight
    )
}

@Composable
fun IconoBus() {
    Icon(
        painter = painterResource(id = R.drawable.ic_bus),
        contentDescription = "Bus",
        tint = Color.Black,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
    )
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
