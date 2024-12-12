package com.example.chauffeur.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chauffeur.databinding.ItemRideCardBinding
import com.example.chauffeur.model.Driver
import com.example.chauffeur.model.ride.requests.RideConfirmRequest
import com.example.chauffeur.model.ride.response.Option
import com.example.chauffeur.model.ride.response.RideResponse

class RideDriverAdapter : ListAdapter<Option, RideDriverAdapter.RideDriverCardHolder>(RideDriverDiffCallBack()) {

    var rideInfo: RideResponse? = null

    inner class RideDriverCardHolder(private val binding: ItemRideCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Option) {
            with(binding) {
                itemTxtDriverName.text = item.name
                itemTxtDriverDescription.text = item.review.comment
                itemTxtDriverVehicle.text = item.vehicle
                itemTxtRidePrice.text = item.value.toString()
                itemRatingbarDriver.numStars = item.review.rating
                itemTxtRatingDriver.text = item.review.rating.toString()

            }
            binding.btnConfirmRide.setOnClickListener {
                val confirmationRideInfo: RideConfirmRequest? = rideInfo?.let { rideResponse ->
                    rideResponse.rideRequest?.let {
                        RideConfirmRequest(
                            rideRequest = it,
                            distance = rideResponse.distance,
                            duration = rideResponse.duration.toString(),
                            value = item.value,
                            driver = Driver(id = item.id, name = item.name)

                        )
                    }
                }

                Log.i("TAG", "bind: DRIVER $confirmationRideInfo")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideDriverCardHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRideCardBinding = ItemRideCardBinding.inflate(layoutInflater, parent, false)
        return RideDriverCardHolder(binding)
    }

    override fun onBindViewHolder(holder: RideDriverCardHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RideDriverDiffCallBack : DiffUtil.ItemCallback<Option>() {
    override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean = oldItem == newItem
}