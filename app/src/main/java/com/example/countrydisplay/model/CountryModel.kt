package com.example.countrydisplay.model

import com.google.gson.annotations.SerializedName

typealias CountryList = List<CountryModel>

data class CountryModel(
    @SerializedName("capital")
    val capital: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("flag")
    val flagUrl: String,
)
