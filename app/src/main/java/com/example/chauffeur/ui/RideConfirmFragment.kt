package com.example.chauffeur.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chauffeur.databinding.FragmentRideConfirmBinding
import com.example.chauffeur.model.ride.response.Option
import com.example.chauffeur.model.ride.response.RideResponse
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

        val rideResponseArgs: RideResponse = rideConfirmFragmentArgs.rideResponseArgs
        val rideDriverOptions: List<Option> = rideResponseArgs.options

        binding?.rvRideConfirmDriver?.adapter = rideDriverAdapter
        binding?.rvRideConfirmDriver?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        rideDriverAdapter.submitList(rideDriverOptions)
        rideDriverAdapter.rideInfo = rideResponseArgs

        val appInfo = context?.packageManager?.getApplicationInfo(requireContext().packageName, PackageManager.GET_META_DATA)
        val googleApiKey: String? = appInfo?.metaData?.getString("com.google.android.geo.API_KEY")
        val mapUrl = getStaticMapUrl(
            originLatitude = rideResponseArgs.origin.latitude,
            originLongitude = rideResponseArgs.origin.longitude,
            destinationLatitude = rideResponseArgs.destination.latitude,
            destinationLongitude = rideResponseArgs.destination.longitude,
            apiKey = googleApiKey
        )

        binding?.imgStaticMapView?.let { Glide.with(this).load(mapUrl).into(it) }


    }

   private fun getStaticMapUrl(
        originLatitude: Double,
        originLongitude: Double,
        destinationLatitude: Double,
        destinationLongitude: Double,
        zoom: Int = 12,
        width: Int = 600,
        height: Int = 300,
        apiKey: String?
    ): String {
        return "https://maps.googleapis.com/maps/api/staticmap" +
                "?center=$originLatitude,$originLongitude" +
                "&zoom=$zoom" +
                "&size=${width}x$height" +
                "&path=color:0x0000ff|weight:5|$originLatitude,$originLongitude|$destinationLatitude,$destinationLongitude" +
                "&markers=color:green|label:O|$originLatitude,$originLongitude" +
                "&markers=color:red|label:D|$destinationLatitude,$destinationLongitude" +
                "&key=$apiKey"
    }
}