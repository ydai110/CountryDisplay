package com.example.countrydisplay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrydisplay.api.CountryDisplayRepository

class CountryDisplayFactory(private val repository: CountryDisplayRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryDisplayViewModel::class.java)) {
            CountryDisplayViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
