package com.example.yameritoxmlcompose.helpers

class Distancia {

    fun CalcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {

        val radioTierra = 6378.137

        var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180
        var dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180

        var a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2)

        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

        var d = radioTierra * c

        return d * 1000
    }
}