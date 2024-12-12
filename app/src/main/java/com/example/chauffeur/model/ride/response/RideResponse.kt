package com.example.chauffeur.model.ride.response

import java.io.Serializable

data class RideResponse(
    val origin: OriginResponse,
    val destination: DestinationResponse,
    val distance: Int,
    val duration: Int,
    val options: List<Option>
) : Serializable

data class OriginResponse(
    val latitude: Double,
    val longitude: Double
)

data class DestinationResponse(
    val latitude: Double,
    val longitude: Double
)

data class Option(
    val id: Int,
    val name: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)

data class Review(
    val rating: Int,
    val comment: String,
)