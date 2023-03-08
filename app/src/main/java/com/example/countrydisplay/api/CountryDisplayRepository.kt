package com.example.countrydisplay.api

import com.example.countrydisplay.model.CountryList
import retrofit2.Response

interface CountryDisplayRepository {
    suspend fun requestAllCountries(): Response<CountryList>
}

class CountryDisplayRepositoryImpl(
    private val countryApi: CountryApi
) : CountryDisplayRepository {

    // Request All countries
    override suspend fun requestAllCountries(): Response<CountryList> = countryApi.requestCountry()
}
