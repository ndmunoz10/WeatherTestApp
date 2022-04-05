package com.nicdamun.weatherapp.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.common.models.LocationModel
import com.nicdamun.weatherapp.R
import com.nicdamun.weatherapp.databinding.LocationItemBinding
import com.nicdamun.weatherapp.utils.LocationDiffUtils

class LocationAdapter(
    private val onTap: (LocationModel) -> Unit
): ListAdapter<LocationModel, LocationAdapter.LocationViewHolder>(LocationDiffUtils()) {

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class LocationViewHolder(
        private val binding: LocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(location: LocationModel) {
            with(binding) {
                tvLocationItemTitle.text = location.title
                tvLocationItemType.text = location.locationType
                tvLocationDistance.text = location.distance?.let { distance ->
                    root.context.getString(R.string.x_km, distance)
                } ?: root.context.getString(R.string.none)
                root.setOnClickListener { onTap(location) }
            }
        }
    }
}