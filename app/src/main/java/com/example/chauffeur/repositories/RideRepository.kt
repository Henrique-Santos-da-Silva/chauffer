package com.example.chauffeur.repositories

import com.example.chauffeur.http.AppApiEndPoint
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.Options
import com.example.chauffeur.util.Resource

class RideRepository(private val api: AppApiEndPoint): BaseRepository() {

    suspend fun estimativeTravel(ride: RideRequest): Resource<Options> {
        return safeApiCall { api.estimateTravel(ride) }
    }
}