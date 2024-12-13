package com.example.chauffeur.model.ride.requests

import com.example.chauffeur.model.Driver
import com.google.gson.annotations.SerializedName

data class RideConfirmRequest(
    @SerializedName("customer_id")
    var customerId: String = "",
    var origin: String,
    var destination: String,
    var distance: Int,
    var duration: String,
    var driver: Driver,
    var value: Double)
