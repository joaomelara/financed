package com.example.financeseducation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)) {
        NavItem("Profile", modifier = Modifier.weight(1f), navController)
        NavItem("Converter", modifier = Modifier.weight(1f), navController)
        NavItem("Map", modifier = Modifier.weight(1f), navController)
    }
}

@Composable
fun NavItem(label: String, modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable { navController.navigate(label) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = label)
    }
}