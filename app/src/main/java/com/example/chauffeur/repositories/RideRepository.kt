package com.example.chauffeur.repositories

import com.example.chauffeur.http.AppApiEndPoint
import com.example.chauffeur.model.ride.requests.RideConfirmRequest
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideConfirmResponse
import com.example.chauffeur.model.ride.response.RideHistoryResponse
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.util.Resource

class RideRepository(private val api: AppApiEndPoint): BaseRepository() {

    suspend fun estimateTravel(ride: RideRequest): Resource<RideResponse> {
        return safeApiCall { api.estimateTravel(ride) }
    }

    suspend fun confirmTravel(rideConfirmData: RideConfirmRequest): Resource<RideConfirmResponse> {
        return safeApiCall { api.rideConfirm(rideConfirmData) }
    }

    suspend fun listTravelHistory(customerId: String, driverId: String? = null): Resource<RideHistoryResponse> {
        return safeApiCall { api.listTravelHistory(customerId, driverId) }
    }
}