package com.example.chauffeur.model.ride.requests

import com.example.chauffeur.model.Driver

data class RideConfirmRequest(
    var rideRequest: RideRequest,
    var distance: Int,
    var duration: String,
    var driver: Driver,
    var value: Double)
