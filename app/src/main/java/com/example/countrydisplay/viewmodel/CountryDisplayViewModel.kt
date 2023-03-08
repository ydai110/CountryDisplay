package com.example.countrydisplay.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrydisplay.Result
import com.example.countrydisplay.api.CountryDisplayRepository
import com.example.countrydisplay.extension.checkInternet
import com.example.countrydisplay.model.CountryList
import kotlinx.coroutines.launch

class CountryDisplayViewModel(
    private val countryRepository: CountryDisplayRepository
) : ViewModel() {

    private val _countryLiveData: MutableLiveData<Result<CountryList>> = MutableLiveData()
    val countryLiveData: LiveData<Result<CountryList>> = _countryLiveData

    fun requestAllCountries(context: Context) {
        _countryLiveData.postValue(Result.Loading)
        if (!context.checkInternet()) {
            _countryLiveData.postValue(Result.Failure(msg = "No Internet Connected. Please reconnect it."))
            return
        }
        viewModelScope.launch {
            try {
                val response = countryRepository.requestAllCountries()
                if (response.isSuccessful) {
                    val data = response.body()
                    _countryLiveData.postValue(Result.Success(data as CountryList))
                } else {
                    _countryLiveData.postValue(Result.Failure(msg = response.message()))
                }
            } catch (e: java.lang.Exception) {
                _countryLiveData.postValue(Result.Failure(msg = e.message))
            }
        }
    }
}
