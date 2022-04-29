package com.example.countriesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.databinding.CountryItemBinding
import com.example.countriesapp.model.Country

class CountriesAdapter(
    private val countryItem: MutableList<Country> = mutableListOf()
) : RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryItem[position])
    }

    override fun getItemCount(): Int = countryItem.size

    fun setCountriesData(newCountries: List<Country>) {
        countryItem.clear()
        countryItem.addAll(newCountries)
        notifyDataSetChanged()
    }
}

class CountryViewHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(countryItem: Country) {
        binding.countryName.text = String.format(countryItem.name + ",")
        binding.countryRegion.text = countryItem.region
        binding.countryCode.text = countryItem.code
        binding.countryCapital.text = countryItem.capital
    }
}