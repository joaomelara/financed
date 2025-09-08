package com.example.financeseducation.models

import com.google.gson.annotations.SerializedName

data class Currency(
    val result: String?,
    @SerializedName("base_code") val baseCode: String?,
    @SerializedName("conversion_rates") private val conversionRates: Map<String, Double>?,
    @SerializedName("rates") private val ratesField: Map<String, Double>?
) {
    val rates: Map<String, Double>?
        get() = ratesField ?: conversionRates
}