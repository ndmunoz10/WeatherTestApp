package com.nicdamun.weatherapp.features.locationDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.nicdamun.common.models.ConsolidatedWeatherModel
import com.nicdamun.weatherapp.databinding.WeatherPreviewItemBinding
import com.nicdamun.weatherapp.utils.ConsolidatedWeatherDiffUtils
import com.nicdamun.weatherapp.utils.WeatherUtils
import java.text.SimpleDateFormat
import java.util.Locale

class PreviewWeatherAdapter : ListAdapter<ConsolidatedWeatherModel, PreviewWeatherAdapter.PreviewWeatherViewHolder>(ConsolidatedWeatherDiffUtils()) {

    override fun onBindViewHolder(holder: PreviewWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewWeatherViewHolder {
        return PreviewWeatherViewHolder(
            WeatherPreviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PreviewWeatherViewHolder(
        private val binding: WeatherPreviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(consolidatedWeather: ConsolidatedWeatherModel) {
            with(binding) {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.parse(consolidatedWeather.applicableDate)?.let { actualDate ->
                    val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(actualDate)
                    tvPreviewDay.text = dayOfWeek
                }
                tvPreviewTemp.text = consolidatedWeather.theTemp.toString()
                with(lvPreviewWeather) {
                    setAnimation(WeatherUtils.setWeatherAnimation(consolidatedWeather.weatherStateAbbr))
                    repeatCount = LottieDrawable.INFINITE
                    playAnimation()
                }
            }
        }
    }
}