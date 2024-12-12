package com.example.chauffeur.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chauffeur.databinding.FragmentRideConfirmBinding
import com.example.chauffeur.model.ride.response.Option
import com.example.chauffeur.ui.adapters.RideDriverAdapter
import org.koin.android.ext.android.inject

class RideConfirmFragment : Fragment() {
    private var _binding: FragmentRideConfirmBinding? = null
    private val binding: FragmentRideConfirmBinding? get() = _binding

    private val rideConfirmFragmentArgs: RideConfirmFragmentArgs by navArgs()

    private val rideDriverAdapter: RideDriverAdapter by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRideConfirmBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("TAG3", "onViewCreated: ${rideConfirmFragmentArgs.rideResponseArgs}")

        val rideDriverOptions: List<Option> = rideConfirmFragmentArgs.rideResponseArgs.options

        binding?.rvRideConfirmDriver?.adapter = rideDriverAdapter
        binding?.rvRideConfirmDriver?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        rideDriverAdapter.submitList(rideDriverOptions)

    }
}