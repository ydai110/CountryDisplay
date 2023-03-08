package com.example.countrydisplay.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrydisplay.R
import com.example.countrydisplay.Result
import com.example.countrydisplay.api.BASE_URL
import com.example.countrydisplay.api.CountryApi
import com.example.countrydisplay.api.CountryDisplayRepository
import com.example.countrydisplay.api.CountryDisplayRepositoryImpl
import com.example.countrydisplay.model.CountryList
import com.example.countrydisplay.viewmodel.CountryDisplayFactory
import com.example.countrydisplay.viewmodel.CountryDisplayViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit

    private lateinit var countryAdapter: CountryDisplayAdapter

    private lateinit var viewModel: CountryDisplayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        initRecycleView()
        initViewModel()
    }

    private fun initRecycleView() {
        countryAdapter = CountryDisplayAdapter(mutableListOf())
        recyclerview_container.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun initViewModel() {
        val api = retrofit.create(CountryApi::class.java)
        val countryDisplayFactory = CountryDisplayFactory(
            repository = CountryDisplayRepositoryImpl(countryApi = api)
        )

        val vm: CountryDisplayViewModel by viewModels { countryDisplayFactory }
        viewModel = vm
        viewModel.requestAllCountries(this)

        viewModel.countryLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success ->
                    handleResult(result.value)
                is Result.Failure ->
                    displayErrorMsg(result.msg)
                is Result.Loading -> Unit
            }
        })
    }

    private fun handleResult(countryList: CountryList?) {
        if (countryList != null) {
            countryAdapter.setData(countryList)
        }
    }

    private fun displayErrorMsg(msg: String?) {
        Snackbar.make(container, msg ?: "", Snackbar.LENGTH_SHORT).show()
    }
}