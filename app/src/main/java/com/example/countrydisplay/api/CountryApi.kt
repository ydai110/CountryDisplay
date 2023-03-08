package com.example.countrydisplay.api

import com.example.countrydisplay.model.CountryList
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://gist.githubusercontent.com/"

interface CountryApi {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun requestCountry(): Response<CountryList>
}
