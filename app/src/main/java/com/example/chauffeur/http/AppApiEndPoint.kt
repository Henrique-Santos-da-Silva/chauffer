package com.example.chauffeur.http

import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.util.Resource
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiEndPoint {

    @GET("ride/{customer_id}")
    suspend fun listTravelHistory(@Path("customer_id") customerId: String, @Query("driver_id") driverId: String? = null) : Result<Response<ResponseBody>>

    @POST("ride/estimate")
    suspend fun estimateTravel(@Body ride: RideRequest) : Response<RideResponse>

    @PATCH
    suspend fun rideConfirm(@Body ride: RideRequest): Resource<Response<ResponseBody>>
}