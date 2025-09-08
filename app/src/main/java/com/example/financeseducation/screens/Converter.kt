package com.example.financeseducation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeseducation.components.NavBar
import com.example.financeseducation.models.Currency
import com.example.financeseducation.services.CurrencyService
import com.example.financeseducation.services.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun Converter(navController: NavController) {
    val currencies = listOf("USD", "BRL", "EUR", "CNY", "JPY", "GBP", "AOA", "ARS")

    var topAmount by rememberSaveable { mutableStateOf("") }
    var topCurrency by rememberSaveable { mutableStateOf(currencies[0]) }
    var topExpanded by remember { mutableStateOf(false) }

    var bottomAmount by rememberSaveable { mutableStateOf("") }
    var bottomCurrency by rememberSaveable { mutableStateOf(currencies[1]) }
    var bottomExpanded by remember { mutableStateOf(false) }

    val canConvert = topAmount.isNotBlank() && topCurrency != bottomCurrency

    // Use your RetrofitFactory to get the service
    val currencyService: CurrencyService = RetrofitFactory().getCurrencyService()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 70.dp, 16.dp, 115.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Conversor",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(modifier = Modifier.align(Alignment.Start), text = "De")

            // Top row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = topAmount,
                    onValueChange = { novoValor ->
                        topAmount = novoValor.filter { it.isDigit() || it == '.' || it == ',' }
                    },
                    label = { Text("Valor") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = topExpanded,
                    onExpandedChange = { topExpanded = !topExpanded },
                    modifier = Modifier.width(140.dp)
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = topCurrency,
                        onValueChange = {},
                        label = { Text("Moeda") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = topExpanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    DropdownMenu(
                        expanded = topExpanded,
                        onDismissRequest = { topExpanded = false }
                    ) {
                        currencies.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection) },
                                onClick = {
                                    if (selection != bottomCurrency) {
                                        topCurrency = selection
                                    } else {
                                        bottomCurrency = topCurrency
                                        topCurrency = selection
                                    }
                                    topExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(modifier = Modifier.align(Alignment.Start), text = "Para")

            // Bottom row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = bottomAmount,
                    onValueChange = {},
                    label = { Text("Valor") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = bottomExpanded,
                    onExpandedChange = { bottomExpanded = !bottomExpanded },
                    modifier = Modifier.width(140.dp)
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = bottomCurrency,
                        onValueChange = {},
                        label = { Text("Moeda") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = bottomExpanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    DropdownMenu(
                        expanded = bottomExpanded,
                        onDismissRequest = { bottomExpanded = false }
                    ) {
                        currencies.forEach { selection ->
                            DropdownMenuItem(
                                text = { Text(selection) },
                                onClick = {
                                    if (selection != topCurrency) {
                                        bottomCurrency = selection
                                    } else {
                                        topCurrency = bottomCurrency
                                        bottomCurrency = selection
                                    }
                                    bottomExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                if (!canConvert) return@Button

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val normalized = topAmount.replace(',', '.')
                        val amount = normalized.toDoubleOrNull()
                        if (amount == null) {
                            withContext(Dispatchers.Main) { bottomAmount = "" }
                            return@launch
                        }

                        val resp: Currency = currencyService.getCurrency(topCurrency)
                        val rate = resp.rates?.get(bottomCurrency)

                        withContext(Dispatchers.Main) {
                            if (rate != null) {
                                val converted = amount * rate
                                bottomAmount = String.format("%.2f", converted)
                            } else {
                                bottomAmount = ""
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            bottomAmount = ""
                        }
                    }
                }
            }) {
                Text(fontSize =  18.sp, modifier = Modifier.padding(vertical = 6.dp) ,text = "Converter")
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            NavBar(navController)
        }
    }
}
