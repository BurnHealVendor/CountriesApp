package com.example.countriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.rest.CountriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepo: CountriesRepo,
    private var ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _countriesLiveData: MutableLiveData<CountriesState> = MutableLiveData(CountriesState.LOADING)
    val countriesLiveData: LiveData<CountriesState> get() = _countriesLiveData

    fun getCountries() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = countriesRepo.getCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _countriesLiveData.postValue(CountriesState.SUCCESS(it))
                    } ?: throw Exception("Response is null")
                }
                else {
                    throw Exception("No successful response")
                }
            }
            catch (e: Exception) {
                _countriesLiveData.postValue(CountriesState.ERROR(e))
            }
        }
    }
}