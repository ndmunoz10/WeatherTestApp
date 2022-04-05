package com.nicdamun.weatherapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.nicdamun.common.models.ConsolidatedWeatherModel

class ConsolidatedWeatherDiffUtils: DiffUtil.ItemCallback<ConsolidatedWeatherModel>() {

    override fun areContentsTheSame(
        oldItem: ConsolidatedWeatherModel,
        newItem: ConsolidatedWeatherModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(
        oldItem: ConsolidatedWeatherModel,
        newItem: ConsolidatedWeatherModel
    ): Boolean {
        return oldItem == newItem
    }
}