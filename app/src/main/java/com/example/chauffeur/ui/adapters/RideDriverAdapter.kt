package com.example.chauffeur.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chauffeur.R
import com.example.chauffeur.databinding.ItemRideCardBinding
import com.example.chauffeur.model.Driver
import com.example.chauffeur.model.ride.requests.RideConfirmRequest
import com.example.chauffeur.model.ride.response.Option
import com.example.chauffeur.model.ride.response.RideResponse
import com.example.chauffeur.util.Resource
import com.example.chauffeur.viewmodel.RideViewModel

class RideDriverAdapter(private val rideViewModel: RideViewModel, private val navController: NavController) : ListAdapter<Option, RideDriverAdapter.RideDriverCardHolder>(RideDriverDiffCallBack()) {

    var rideInfo: RideResponse? = null

    inner class RideDriverCardHolder(private val binding: ItemRideCardBinding, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Option) {
            with(binding) {
                itemTxtDriverName.text = itemView.resources.getString(R.string.ride_history_driver_name, item.name)
                itemTxtDriverDescription.text = itemView.resources.getString(R.string.ride_history_driver_desc, item.review.comment)
                itemTxtDriverVehicle.text = itemView.resources.getString(R.string.ride_history_vehicle, item.vehicle)
                itemTxtRidePrice.text = itemView.resources.getString(R.string.ride_history_price, item.value)
                itemRatingbarDriver.rating = item.review.rating.toFloat()
                itemTxtRatingDriver.text = item.review.rating.toString()

            }
            binding.btnConfirmRide.setOnClickListener {
                val confirmationRideInfo: RideConfirmRequest? = rideInfo?.let { rideResponse ->
                    rideResponse.rideRequest?.let {
                        RideConfirmRequest(
                            customerId = it.customerId,
                            origin = it.origin,
                            destination = it.destination,
                            distance = rideResponse.distance,
                            duration = rideResponse.duration.toString(),
                            value = item.value,
                            driver = Driver(id = item.id, name = item.name)

                        )
                    }
                }

                confirmationRideInfo?.let { cri -> rideViewModel.confirmRide(cri) }

                rideViewModel.isRideConfirm.observe(lifecycleOwner) { resource ->
                    when(resource) {
                        is Resource.Success -> {
                            if (resource.data?.success != null) {
                                navController.navigate(R.id.action_rideConfirmFragment_to_rideHistoryFragment)
                            }
                            Log.i("TAG", "bind: DRIVER ${resource.data?.success}")
                        }
                        is Resource.Error -> Log.i("TAG", "bind: DRIVER ERROR")
                        is Resource.Loading -> Log.i("TAG", "bind: DRIVER LOADING")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideDriverCardHolder {
        val lifecycleOwner = parent.context as LifecycleOwner
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRideCardBinding = ItemRideCardBinding.inflate(layoutInflater, parent, false)
        return RideDriverCardHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: RideDriverCardHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RideDriverDiffCallBack : DiffUtil.ItemCallback<Option>() {
    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean = oldItem == newItem
}