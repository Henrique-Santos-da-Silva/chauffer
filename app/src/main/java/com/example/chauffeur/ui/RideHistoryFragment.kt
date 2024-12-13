package com.example.chauffeur.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chauffeur.databinding.FragmentRideHistoryBinding
import com.example.chauffeur.ui.adapters.RideHistoryAdapter
import com.example.chauffeur.util.Resource
import com.example.chauffeur.viewmodel.RideViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RideHistoryFragment : Fragment() {
    private var _binding: FragmentRideHistoryBinding? = null
    private val binding: FragmentRideHistoryBinding? get() = _binding

    private val rideViewModel: RideViewModel by viewModel()

    private val rideHistoryAdapter: RideHistoryAdapter by inject()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRideHistoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding?.let { fragmentRideHistoryBinding ->
            with(fragmentRideHistoryBinding) {
                btnFilterRideHistory.setOnClickListener {
                    val customerId = rideHistoryTxtUserId.text.toString()
                    val driverId = rideHistoryTxtDriverId.text.toString()

                    rideViewModel.getRideHistory(customerId, driverId)
                }

                rvRideHistory.adapter = rideHistoryAdapter
                rvRideHistory.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                rideViewModel.listRideHistory.observe(viewLifecycleOwner, Observer { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            resource.data?.let { Log.i("TAG2", "uiSetup: ${it.rides}") }
                            resource.data?.let { rideHistoryAdapter.submitList(it.rides) }
                        }
                        is Resource.Error -> Log.i("TAG2", "uiSetup: ${resource.code} ${resource.message}")
                        is Resource.Loading -> Log.i("TAG2", "uiSetup: LOOOOOOOOOOOOOOOOOOOOOOOOOOOOAAAAAAAAAAAAAAAAAAAADDDDDDDDDDDING")
                    }

                })
            }
        }
    }
}