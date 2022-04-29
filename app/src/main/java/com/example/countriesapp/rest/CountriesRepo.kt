package com.example.countriesapp.rest

import com.example.countriesapp.model.Country
import retrofit2.Response

interface CountriesRepo {

    suspend fun getCountries(): Response<List<Country>>
}

class CountriesRepoImpl(
    private val countriesAPI: CountriesAPI
) : CountriesRepo {

    override suspend fun getCountries(): Response<List<Country>> =
        countriesAPI.getCountries()
}