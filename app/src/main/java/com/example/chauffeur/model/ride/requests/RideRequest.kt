package com.example.chauffeur.model.ride.requests

import com.example.chauffeur.model.Driver
import com.google.gson.annotations.SerializedName

data class RideRequest(
    @SerializedName("customer_id")
    var customerId: String = "",
    var origin: String,
    var destination: String
) {
    constructor(customerId: String, origin: String, destination: String, distance: Int, duration: String, driver: Driver, value: Int)
            : this(customerId, origin, destination)
}
