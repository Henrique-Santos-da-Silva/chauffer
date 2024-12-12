package com.example.chauffeur.model.ride.requests

import com.google.gson.annotations.SerializedName

data class RideRequest(
    @SerializedName("customer_id")
    var customerId: String = "",
    var origin: String,
    var destination: String
)
