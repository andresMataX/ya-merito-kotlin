package com.example.yameritoxmlcompose.ui.screens.confirm.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yameritoxmlcompose.R
import com.example.yameritoxmlcompose.ui.fragments.LocalizationViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ConfirmScreen(
    navController: NavController,
    confirmViewModel: ConfirmViewModel,
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
        Confirm(navController, confirmViewModel, maliFamiliy, text, localizationViewModel)
    }
}

@Composable
fun Confirm(
    navController: NavController,
    confirmViewModel: ConfirmViewModel,
    maliFamiliy: FontFamily,
    text: String?,
    localizationViewModel: LocalizationViewModel
) {
    // Confirm
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Mapa
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.5f)
                .background(Color.Magenta)
        ) { }
//        MyGoogleMaps(confirmViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        // FormConfirm
        FormConfirm(maliFamiliy, navController, confirmViewModel, text, localizationViewModel)
    }
}

/*@Composable
fun MyGoogleMaps(confirmViewModel: ConfirmViewModel) {

    val range: Int by confirmViewModel.range.observeAsState(initial = 1500)

    val marker = LatLng(25.7250337, -100.3156971)

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
        Marker(
            position = marker,
            title = "Vive la FIME :D",
            snippet = "Chapa estuvo aquí",
        )
        Circle(
            center = marker,
            radius = range.toDouble(), // en metros
            strokeColor = Color.Black,
            visible = true,
            strokeWidth = 5.0F
        )
    }
}
*/
@Composable
fun FormConfirm(
    maliFamiliy: FontFamily,
    navController: NavController,
    confirmViewModel: ConfirmViewModel,
    text: String?,
    localizationViewModel: LocalizationViewModel
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DestinoFormConfirm(maliFamiliy, text, localizationViewModel)
        ZonaFormConfirm(maliFamiliy, confirmViewModel)
        Spacer(modifier = Modifier.height(80.dp))
        ButtonsConfirm(maliFamiliy, navController)
    }
}

@Composable
fun ButtonsConfirm(maliFamiliy: FontFamily, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BotonFormCancelar(maliFamiliy, navController)
        BotonFormAceptar(maliFamiliy)
    }
}

@Composable
fun DestinoFormConfirm(
    maliFamiliy: FontFamily,
    text: String?,
    localizationViewModel: LocalizationViewModel
) {

    val markerFIME = LatLng(25.7250337, -100.3156971)

    val latUser: Double by localizationViewModel.lat.observeAsState(0.0)
    val lonUser: Double by localizationViewModel.lon.observeAsState(0.0)

    localizationViewModel.onChangeCoordenadas(
        latUser,
        lonUser,
        markerFIME.latitude,
        markerFIME.longitude
    )

    val distancia: Double by localizationViewModel.distancia.observeAsState(0.0)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Destino",
            fontSize = 18.sp,
            fontFamily = maliFamiliy,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Distancia con FIME $distancia",
            fontSize = 24.sp,
            fontFamily = maliFamiliy,
            fontWeight = FontWeight.Bold
        )
        InputDestino(text, maliFamiliy)
    }
}

@Composable
fun InputDestino(text: String?, maliFamiliy: FontFamily) {
    BasicTextField(
        value = text.toString(),
        onValueChange = { }, // No es necesario cambiarse.
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxWidth()
            .border(
                1.dp,
                Color.Black,
                RoundedCornerShape(8.dp)
            )
            .padding(start = 4.dp),
        textStyle = TextStyle(
            fontFamily = maliFamiliy,
            fontWeight = FontWeight.Light,
            fontSize = 22.sp,
        ),
        readOnly = true,
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun ZonaFormConfirm(maliFamiliy: FontFamily, confirmViewModel: ConfirmViewModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabelInputZona(
            maliFamiliy,
            { confirmViewModel.onRangeChange(it) },
            { confirmViewModel.onSendRange(it) })
    }
}

@Composable
fun LabelInputZona(
    maliFamiliy: FontFamily,
    onRangeChange: (Int) -> Unit,
    onSendChange: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Zona de alarma (m)",
            fontSize = 18.sp,
            fontFamily = maliFamiliy,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        InputZona(maliFamiliy, onRangeChange, onSendChange)
    }
}

@Composable
fun InputZona(
    maliFamiliy: FontFamily,
    onRangeChange: (Int) -> Unit,
    onSendChange: (Boolean) -> Unit
) {

    var localRange by remember { mutableStateOf(1500) }

    BasicTextField(
        value = localRange.toString(),
        onValueChange = {
            if (it.isNotEmpty() && it.length < 6) {
                localRange = it.toInt()
            } else {
                localRange = 0
            }
        },
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxWidth(0.6f)
            .border(
                1.dp,
                Color.Black,
                RoundedCornerShape(8.dp)
            )
            .padding(start = 4.dp),
        textStyle = TextStyle(
            fontFamily = maliFamiliy,
            fontWeight = FontWeight.Light,
            fontSize = 22.sp,
        ),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Send,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onSend = {
            // TODO: Validación de no menos de 500 m y no más de 10,000m XD?
            onRangeChange(localRange)
            onSendChange(true)
        })
    )
}

@Composable
fun BotonFormCancelar(maliFamiliy: FontFamily, navController: NavController) {
    TextButton(
        onClick = { navController.popBackStack() },
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cancelar",
                fontSize = 18.sp,
                fontFamily = maliFamiliy,
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_cancelar),
                contentDescription = "Cancelar",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun BotonFormAceptar(maliFamiliy: FontFamily) {
    TextButton(
        onClick = { },
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Aceptar",
                fontSize = 18.sp,
                fontFamily = maliFamiliy,
                color = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Aceptar",
                tint = Color.Black
            )
        }
    }
}