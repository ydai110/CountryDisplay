package com.example.countrydisplay

sealed class Result<out T> {
    data class Success<out R>(val value: R) : Result<R>()

    data class Failure(val msg: String?) : Result<Nothing>()

    object Loading : Result<Nothing>()
}
