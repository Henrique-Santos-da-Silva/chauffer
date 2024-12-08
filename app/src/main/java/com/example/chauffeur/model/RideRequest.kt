package com.example.chauffeur.model

import com.google.gson.annotations.SerializedName

data class RideRequest(
    @SerializedName("customer_id")
    val customerId: String = "",
    val origin: String,
    val destination: String
) {
    constructor(customerId: String, origin: String, destination: String, distance: Int, duration: String, driver: Driver, value: Int)
            : this(customerId, origin, destination)
}
