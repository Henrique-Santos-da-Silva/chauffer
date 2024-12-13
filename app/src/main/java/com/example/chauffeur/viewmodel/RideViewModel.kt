package com.example.chauffeur.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chauffeur.model.ride.requests.RideConfirmRequest
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideConfirmResponse
import com.example.chauffeur.model.ride.response.RideHistory
import com.example.chauffeur.model.ride.response.RideHistoryResponse
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.repositories.RideRepository
import com.example.chauffeur.util.Resource
import kotlinx.coroutines.launch

class RideViewModel(private val repository: RideRepository) : ViewModel() {
    private val _optionsRide = MutableLiveData<Resource<RideResponse>>()
    val optionsRide: LiveData<Resource<RideResponse>> = _optionsRide

    private val _isRideConfirm = MutableLiveData<Resource<RideConfirmResponse>>()
    val isRideConfirm: LiveData<Resource<RideConfirmResponse>> = _isRideConfirm

    private val _listRideHistory = MutableLiveData<Resource<RideHistoryResponse>>()
    val listRideHistory: LiveData<Resource<RideHistoryResponse>> = _listRideHistory

    fun estimateRide(ride: RideRequest) {
        viewModelScope.launch {
            _optionsRide.postValue(Resource.Loading())

            _optionsRide.postValue(repository.estimateTravel(ride))
        }
    }

    fun confirmRide(rideConfirmData: RideConfirmRequest) {
        viewModelScope.launch {
            _isRideConfirm.postValue(Resource.Loading())

            _isRideConfirm.postValue(repository.confirmTravel(rideConfirmData))
        }
    }

    fun getRideHistory(customerId: String, driverId: String? = null) {
        viewModelScope.launch {
            _listRideHistory.postValue(Resource.Loading())
            _listRideHistory.postValue(repository.listTravelHistory(customerId, driverId))
        }
    }
}