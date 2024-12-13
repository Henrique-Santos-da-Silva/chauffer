package com.example.chauffeur.model.ride.response

import com.example.chauffeur.model.Driver

data class RideHistory(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double,
)


