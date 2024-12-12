package com.example.chauffeur.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.repositories.RideRepository
import com.example.chauffeur.util.Resource
import kotlinx.coroutines.launch

class RideViewModel(private val repository: RideRepository) : ViewModel() {
    private val _optionsRide = MutableLiveData<Resource<RideResponse>>()
    val optionsRide: LiveData<Resource<RideResponse>> = _optionsRide

    fun estimateRide(ride: RideRequest) {
        viewModelScope.launch {
            _optionsRide.postValue(Resource.Loading())

            _optionsRide.postValue(repository.estimateTravel(ride))
        }
    }
}