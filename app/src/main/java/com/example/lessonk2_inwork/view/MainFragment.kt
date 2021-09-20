package com.example.lessonk2_inwork.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lessonk2_inwork.R
import com.example.lessonk2_inwork.databinding.FragmentMainBinding
import com.example.lessonk2_inwork.domen.Weather
import com.example.lessonk2_inwork.view.DetailFragment.Companion.WEATHER_KEY
import com.example.lessonk2_inwork.viewmodel.AppState
import com.example.lessonk2_inwork.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), OnItemClickListener {
    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private var isRussianFlag = true
    private val adapter = MainFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveDataToObserver()
            .observe(viewLifecycleOwner, Observer {
                renderDate(it)
            })
        binding.mainFragmentRecyclerView.adapter = adapter
        adapter.seOnItemClickListener(this)
        binding.mainFragmentFAB.setOnClickListener {
            if (isRussianFlag) {
                viewModel.getDataFromLocalSourceWorld()
                isRussianFlag = false
                binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getDataFromLocalSourceRussian()
                isRussianFlag = true
                binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
            }
        }

        if (savedInstanceState == null)
            viewModel.getDataFromLocalSourceRussian()
    }

    private fun renderDate(state: AppState) {
        when (state) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                showSnackBar(state.error.toString())
            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
                showSnackBar("LOADING")
            }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(state.weatherData)
                showSnackBar("Success")
            }
        }
    }

    private fun showSnackBar(message: String) {
        val snackBar: Snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(WEATHER_KEY, weather)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, DetailFragment.newInstance(bundle))
            .addToBackStack("")
            .commit()
    }
}