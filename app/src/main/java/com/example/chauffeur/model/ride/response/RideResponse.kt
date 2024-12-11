package com.example.chauffeur.model.ride.response

data class RideResponse(
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

data class Options(
    val options: List<RideResponse>
)