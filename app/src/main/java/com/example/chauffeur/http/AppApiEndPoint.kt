package com.example.chauffeur.http

import com.example.chauffeur.model.RideRequest
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiEndPoint {

    @GET("ride/{customer_id}")
    suspend fun listTravelHistory(@Path("customer_id") customerId: String, @Query("driver_id") driverId: String? = null) : Result<Response>

    @POST("ride/estimate")
    suspend fun estimateTravel(@Body ride: RideRequest) : Result<Response>

    @PATCH
    suspend fun rideConfirm(@Body ride: RideRequest): Result<Response>
}