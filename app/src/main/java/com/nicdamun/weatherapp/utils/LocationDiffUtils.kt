package com.nicdamun.weatherapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.nicdamun.common.models.LocationModel

class LocationDiffUtils : DiffUtil.ItemCallback<LocationModel>() {

    override fun areContentsTheSame(
        oldItem: LocationModel,
        newItem: LocationModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: LocationModel,
        newItem: LocationModel
    ): Boolean {
        return oldItem.woeid == newItem.woeid
    }
}