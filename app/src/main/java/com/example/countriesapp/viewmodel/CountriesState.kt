package com.example.countriesapp.viewmodel

sealed interface CountriesState {
    object LOADING : CountriesState
    class SUCCESS<T>(val countries: T) : CountriesState
    class ERROR(val error: Throwable) : CountriesState
}