package com.example.chauffeur.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chauffeur.databinding.FragmentMainBinding
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.util.Resource
import com.example.chauffeur.viewmodel.RideViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding? get() = _binding

    private val rideViewModel: RideViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiSetup()
    }

    private fun uiSetup() {
        binding?.let { fragmentMain ->
            with(fragmentMain) {
                formMainEstimativeButton.setOnClickListener {
                    val newRide = RideRequest(
                        customerId = formMainUserId.text.toString(),
                        origin = formMainOriginAddress.text.toString(),
                        destination = formMainDestinationAddress.text.toString()
                    )
                    val xpto: String = formMainUserId.text.toString()
                    Log.i("TAG", "uiSetup: $newRide")
                    Log.i("TAG", "uiSetup: $xpto")

                    rideViewModel.estimateRide(newRide)

                }

                rideViewModel.optionsRide.observe(viewLifecycleOwner, Observer { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            resource.data?.let { Log.i("TAG2", "uiSetup: $it") }
                            Log.i("TAG2", "uiSetup: ${resource.data}")
                            resource.data?.let { findNavController().navigate(MainFragmentDirections.actionMainFragmentToRideConfirmFragment(it)) }
                        }
                        is Resource.Error -> Log.i("TAG2", "uiSetup: ${resource.code} ${resource.message}")
                        is Resource.Loading -> Log.i("TAG2", "uiSetup: LOOOOOOOOOOOOOOOOOOOOOOOOOOOOAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDING")
                    }
                })
            }
        }
    }
}