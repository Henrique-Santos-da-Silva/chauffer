package com.example.chauffeur.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chauffeur.databinding.FragmentMainBinding
import com.example.chauffeur.model.ride.requests.RideRequest
import com.example.chauffeur.util.Resource
import com.example.chauffeur.util.showProgress
import com.example.chauffeur.viewmodel.RideViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding? get() = _binding

    private val rideViewModel: RideViewModel by viewModel()

    private lateinit var newRide: RideRequest

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
                    newRide = RideRequest(
                        customerId = formMainUserId.text.toString(),
                        origin = formMainOriginAddress.text.toString(),
                        destination = formMainDestinationAddress.text.toString()
                    )

                    rideViewModel.estimateRide(newRide)

                }

                rideViewModel.optionsRide.observe(viewLifecycleOwner, Observer { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            pbMain.progressBar.showProgress(false)
                            resource.data?.let {
                                it.rideRequest = newRide
                                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRideConfirmFragment(it))
                            }
                        }
                        is Resource.Error -> {
                            pbMain.progressBar.showProgress(false)
                            Toast.makeText(requireContext(), "Ocorreu um erro! Confira os dados e tente novamente", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> pbMain.progressBar.showProgress(true)
                    }
                })
            }
        }
    }
}