package com.example.countriesapp.di

import com.example.countriesapp.rest.CountriesAPI
import com.example.countriesapp.rest.CountriesRepo
import com.example.countriesapp.rest.CountriesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesNetworkService(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(CountriesAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CountriesAPI::class.java)

    @Provides
    fun providesOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesCountriesRepo(countriesAPI: CountriesAPI): CountriesRepo =
        CountriesRepoImpl(countriesAPI)

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO
}