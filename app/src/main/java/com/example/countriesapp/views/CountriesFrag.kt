package com.example.countriesapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.adapter.CountriesAdapter
import com.example.countriesapp.databinding.FragmentCountriesBinding
import com.example.countriesapp.model.Country
import com.example.countriesapp.viewmodel.CountriesState
import com.example.countriesapp.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFrag : Fragment() {

    private val countriesViewModel: CountriesViewModel by viewModels()

    private val binding by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    private val countriesAdapter by lazy {
        CountriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.countriesRecView.apply {
            layoutManager = LinearLayoutManager(requireContext() ,LinearLayoutManager.VERTICAL, false)
            adapter = countriesAdapter
        }

        countriesViewModel.countriesLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CountriesState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is CountriesState.SUCCESS<*> -> {
                    countriesAdapter.setCountriesData(state.countries as List<Country>)
                }
                is CountriesState.ERROR -> {
                    Toast.makeText(requireContext(), state.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        countriesViewModel.getCountries()

        return binding.root
    }
}