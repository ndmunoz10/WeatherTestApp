package com.nicdamun.weatherapp.features.locationDetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieDrawable
import com.nicdamun.common.models.LocationDetailModel
import com.nicdamun.weatherapp.databinding.ActivityLocationDetailBinding
import com.nicdamun.weatherapp.utils.WeatherUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailActivity: AppCompatActivity() {

    companion object {
        const val LOCATION_WOEID = "LOCATION_WOEID_KEY"
    }

    private lateinit var adapter: PreviewWeatherAdapter
    private lateinit var binding: ActivityLocationDetailBinding
    private val viewModel by viewModels<LocationDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModelObservers()
        initPreviewWeatherList()
        viewModel.getLocationDetails(getWoeidExtra())
    }

    private fun getWoeidExtra(): Int {
        return intent.getIntExtra(LOCATION_WOEID, 0)
    }

    private fun handleLoadingState() {
        with(binding) {
            rvDetailsNextDaysList.isVisible = false
            pbDetailsLoading.isVisible = true
            groupDetailsNothingFound.isVisible = false
        }
    }

    private fun handleOnLocationDetailRetrievedState(locationDetail: LocationDetailModel) {
        with(binding) {
            rvDetailsNextDaysList.isVisible = true
            groupDetailsNothingFound.isVisible = false
            pbDetailsLoading.isVisible = false
            tvDetailsTitle.text = locationDetail.title
            setCurrentTemp(locationDetail)
            adapter.submitList(locationDetail.consolidatedWeather.drop(1))
        }
    }

    private fun handleOnLocationDetailFailed() {
        with(binding) {
            pbDetailsLoading.isVisible = false
            groupDetailsNothingFound.isVisible = true
            rvDetailsNextDaysList.isVisible = false
        }
    }

    private fun initPreviewWeatherList() {
        adapter = PreviewWeatherAdapter()
        binding.rvDetailsNextDaysList.adapter = adapter
    }

    private fun setCurrentTemp(locationDetail: LocationDetailModel) {
        val currentConsolidatedData = locationDetail.consolidatedWeather.firstOrNull()
        val anim = WeatherUtils.setWeatherAnimation(currentConsolidatedData?.weatherStateAbbr.orEmpty())
        with(binding) {
            tvDetailsTemp.text = currentConsolidatedData?.theTemp.toString()
            with(lvDetailsTempAnim) {
                setAnimation(anim)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        }
    }

    private fun setViewModelObservers() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is LocationDetailViewState.Loading -> handleLoadingState()
                is LocationDetailViewState.OnLocationDetailRetrieved -> {
                    handleOnLocationDetailRetrievedState(state.locationDetail)
                }
                is LocationDetailViewState.OnLocationDetailFailed -> handleOnLocationDetailFailed()
            }
        }
    }
}