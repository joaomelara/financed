package com.example.financeseducation.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://v6.exchangerate-api.com/v6/869bb1a9b56158b50235739f/latest/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCurrencyService(): CurrencyService {
        return retrofitFactory.create(CurrencyService::class.java)
    }

}