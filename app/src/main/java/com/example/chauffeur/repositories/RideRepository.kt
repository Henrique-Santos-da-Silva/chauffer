package com.example.chauffeur.repositories

import com.example.chauffeur.http.AppApiEndPoint
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.util.Resource

class RideRepository(private val api: AppApiEndPoint): BaseRepository() {

    suspend fun estimateTravel(ride: RideRequest): Resource<RideResponse> {
        return safeApiCall { api.estimateTravel(ride) }
    }
}