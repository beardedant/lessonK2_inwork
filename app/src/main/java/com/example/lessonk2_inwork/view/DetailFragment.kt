package com.example.lessonk2_inwork.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lessonk2_inwork.databinding.FragmentDetailsBinding
import com.example.lessonk2_inwork.databinding.FragmentMainBinding
import com.example.lessonk2_inwork.domen.Weather
import com.example.lessonk2_inwork.viewmodel.AppState
import com.example.lessonk2_inwork.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    companion object {
        fun newInstance(bundle: Bundle): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val WEATHER_KEY = "key"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(WEATHER_KEY) ?: Weather()
        setWeatherData(weather)
    }


    private fun setWeatherData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}