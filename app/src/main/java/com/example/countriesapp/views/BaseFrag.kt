package com.example.countriesapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countriesapp.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFrag : Fragment() {

    protected val countriesViewModel: CountriesViewModel by viewModels()
}