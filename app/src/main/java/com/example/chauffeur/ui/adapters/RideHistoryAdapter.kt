package com.example.chauffeur.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chauffeur.R
import com.example.chauffeur.databinding.ItemHistoryBinding
import com.example.chauffeur.model.ride.response.RideHistory
import java.text.SimpleDateFormat
import java.util.Locale

class RideHistoryAdapter : ListAdapter<RideHistory, RideHistoryAdapter.RideHistoryAdapter>(RideHistoryDiffCallBack()) {
    inner class RideHistoryAdapter(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RideHistory) {
            with(binding) {
                itemTxtDriverName.text = itemView.resources.getString(R.string.ride_history_driver_name, item.driver.name)
                itemTxtRideHistoryDate.text = itemView.resources.getString(R.string.ride_history_data, formatDate(item.date))
                itemTxtRideHistoryOrigin.text = itemView.resources.getString(R.string.ride_history_origin, item.origin)
                itemTxtRideHistoryDestination.text = itemView.resources.getString(R.string.ride_history_destination, item.destination)
                itemTxtRideHistoryDistance.text = itemView.resources.getString(R.string.ride_history_distance, item.distance.toString())
                itemTxtRideHistoryTime.text = itemView.resources.getString(R.string.ride_history_time, item.duration)
                itemTxtRideHistoryPrice.text = itemView.resources.getString(R.string.ride_history_price, item.value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideHistoryAdapter {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemHistoryBinding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        return RideHistoryAdapter(binding)
    }

    override fun onBindViewHolder(holder: RideHistoryAdapter, position: Int) {
        holder.bind(getItem(position))
    }
}

class RideHistoryDiffCallBack : DiffUtil.ItemCallback<RideHistory>() {
    override fun areItemsTheSame(oldItem: RideHistory, newItem: RideHistory): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RideHistory, newItem: RideHistory): Boolean = oldItem == newItem
}

private fun formatDate(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())

    val date = inputFormat.parse(input)
    return outputFormat.format(date!!)
}