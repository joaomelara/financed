package com.example.financeseducation.services

import com.example.financeseducation.models.Currency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyService {

    //https://v6.exchangerate-api.com/v6/869bb1a9b56158b50235739f/latest/USD
    @GET("{cur}")
    suspend fun getCurrency(@Path("cur") cur: String): Currency

}