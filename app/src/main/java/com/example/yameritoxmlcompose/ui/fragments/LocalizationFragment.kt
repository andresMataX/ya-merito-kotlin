package com.example.yameritoxmlcompose.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.*
import com.example.yameritoxmlcompose.R
import com.example.yameritoxmlcompose.ui.screens.main.ui.MainViewModel

class LocalizationFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private val localizationViewModel: LocalizationViewModel by activityViewModels()

    private val CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO = 2106
    private val LOG_TAG = "EnviarUbicacion"
    private var haConcedidoPermisos = false

    companion object {
        fun newInstance() = LocalizationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarPermisos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.localitation_fragment, container, false)
    }

    fun imprimirUbicacion(ubicacion: Location) {

        Log.d(LOG_TAG, "Latitud es ${ubicacion.latitude} y la longitud es ${ubicacion.longitude}")
    }

    fun onPermisosConcedidos() {
        // Hasta aquí sabemos que los permisos ya están concedidos
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    imprimirUbicacion(it)
                } else {
                    Log.d(LOG_TAG, "No se pudo obtener la ubicación")
                }
            }

            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    p0 ?: return
                    Log.d(LOG_TAG, "Se recibió una actualización")
                    for (location in p0.locations) {
                        imprimirUbicacion(location)
                        localizationViewModel.onChangeLat(location.latitude, location.longitude)
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            Log.d(LOG_TAG, "Tal vez no solicitaste permiso antes")
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO) {
            val todosLosPermisosConcedidos =
                grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (grantResults.isNotEmpty() && todosLosPermisosConcedidos) {
                haConcedidoPermisos = true;
                onPermisosConcedidos()
                Log.d(LOG_TAG, "El usuario concedió todos los permisos")
            } else {
                Log.d(LOG_TAG, "Uno o más permisos fueron denegados")
            }
        }
    }

    private fun verificarPermisos() {
        val permisos = arrayListOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        // Segundo plano para Android Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permisos.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        val permisosComoArray = permisos.toTypedArray()
        if (tienePermisos(permisosComoArray)) {
            haConcedidoPermisos = true
            onPermisosConcedidos()
            Log.d(LOG_TAG, "Los permisos ya fueron concedidos")
        } else {
            solicitarPermisos(permisosComoArray)
        }
    }

    private fun solicitarPermisos(permisos: Array<String>) {
        Log.d(LOG_TAG, "Solicitando permisos...")
        requestPermissions(
            permisos,
            CODIGO_PERMISOS_UBICACION_SEGUNDO_PLANO
        )
    }

    private fun tienePermisos(permisos: Array<String>): Boolean {
        return permisos.all {
            return ContextCompat.checkSelfPermission(
                requireActivity(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

}