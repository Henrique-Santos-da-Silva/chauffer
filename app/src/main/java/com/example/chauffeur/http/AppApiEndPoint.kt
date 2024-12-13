package com.example.chauffeur.http

import com.example.chauffeur.model.ride.requests.RideConfirmRequest
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideConfirmResponse
import com.example.chauffeur.model.ride.response.RideHistoryResponse
import com.example.chauffeur.model.ride.response.RideResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiEndPoint {

    @GET("ride/{customer_id}")
    suspend fun listTravelHistory(@Path("customer_id") customerId: String, @Query("driver_id") driverId: String? = null) : Response<RideHistoryResponse>

    @POST("ride/estimate")
    suspend fun estimateTravel(@Body ride: RideRequest) : Response<RideResponse>

    @PATCH("ride/confirm")
    suspend fun rideConfirm(@Body ride: RideConfirmRequest): Response<RideConfirmResponse  >
}