package com.example.financeseducation.models

data class Currency(
    val rates: Map<String, Double>?
) {
    fun rateFor(code: String): Double? = rates?.get(code)
}
