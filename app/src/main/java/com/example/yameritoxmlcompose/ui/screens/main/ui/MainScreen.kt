package com.example.yameritoxmlcompose.ui.screens.main.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.yameritoxmlcompose.ui.navigation.AppScreens
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yameritoxmlcompose.R
import com.example.yameritoxmlcompose.ui.fragments.LocalizationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
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
        Main(maliFamiliy, navController, mainViewModel, localizationViewModel)
    }
}

@Composable
fun Main(
    maliFamiliy: FontFamily,
    navController: NavHostController,
    mainViewModel: MainViewModel,
    localizationViewModel: LocalizationViewModel
) {

    val address: String by mainViewModel.address.observeAsState(initial = "")
    val nextEnable: Boolean by mainViewModel.nextEnable.observeAsState(initial = false)

    // Main
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mapa
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxSize(0.5f)
//                .background(Color.Magenta)
//        ) { }
         MyGoogleMaps(localizationViewModel)

        // Formulario Main
        FormMain(maliFamiliy, address, { mainViewModel.onAddressChange(it) }, localizationViewModel)

        // Favoritos
        // TODO: Destinos favoritos
        Spacer(modifier = Modifier.height(72.dp))
        NextButton(maliFamiliy, navController, nextEnable, address)
    }
}

@Composable
fun MyGoogleMaps(localizationViewModel: LocalizationViewModel) {

    val lat: Double by localizationViewModel.lat.observeAsState(0.0)
    val lon: Double by localizationViewModel.lon.observeAsState(0.0)

    val marker = LatLng(lat, lon)

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 13f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.5f)
            .border(
                3.dp,
                Color.Black,
                RoundedCornerShape(8.dp)
            ),
        cameraPositionState = cameraPosition,
    ) {
        cameraPosition.move(CameraUpdateFactory.newLatLng(LatLng(lat, lon)))
        Marker(
            position = marker,
            title = "Micasa",
            snippet = "micasacompañera",
        )
    }
}

@Composable
fun FormMain(
    maliFamiliy: FontFamily,
    address: String,
    onAddressChange: (String) -> Unit,
    localizationViewModel: LocalizationViewModel
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AddressLabel(maliFamiliy, localizationViewModel)
            AddressField(maliFamiliy, address) { onAddressChange(it) }
        }
    }
}

@Composable
fun AddressLabel(maliFamiliy: FontFamily, localizationViewModel: LocalizationViewModel) {

    val address: Double by localizationViewModel.lat.observeAsState(0.0)
    val address2: Double by localizationViewModel.lon.observeAsState(0.0)

    Text(
        text = "¿A dónde vamos? $address $address2",
        fontSize = 24.sp,
        fontFamily = maliFamiliy,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun AddressField(maliFamiliy: FontFamily, address: String, onAddressChange: (String) -> Unit) {

    BasicTextField(
        value = address,
        onValueChange = { onAddressChange(it) },
        modifier = Modifier
            .background(Color(0xFFEFEFEF))
            .fillMaxWidth()
            .border(
                1.dp,
                Color.Black,
                RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        textStyle = TextStyle(
            fontFamily = maliFamiliy,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        maxLines = 1,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar dirección",
                    tint = Color.Black,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                if (address.isEmpty()) {
                    Text(
                        text = "Ingresa la dirección de destino",
                        fontSize = 16.sp,
                        fontFamily = maliFamiliy,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color(0xFF5A5A5A)
                    )
                } else {
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun NextButton(
    maliFamiliy: FontFamily,
    navController: NavController,
    nextEnable: Boolean,
    address: String
) {
    TextButton(
        onClick = {
            navController.navigate(route = AppScreens.ConfirmScreen.route + "/$address")
        },
        border = BorderStroke(1.dp, Color.Black),
        enabled = nextEnable,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (nextEnable) {
                Text(
                    text = "Siguiente",
                    fontSize = 18.sp,
                    fontFamily = maliFamiliy,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_siguiente),
                    contentDescription = "Siguiente",
                    tint = Color.Black
                )
            } else {
                Text(
                    text = "Siguiente",
                    fontSize = 18.sp,
                    fontFamily = maliFamiliy,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_siguiente),
                    contentDescription = "Siguiente",
                    tint = Color.Gray
                )
            }

        }
    }
}