package com.example.financeseducation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

@Composable
fun NavBar(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(bottom = 5.dp)
        .background(MaterialTheme.colorScheme.surfaceContainerLow )
    ) {
        NavItem("Perfil", modifier = Modifier.weight(1f), navController)
        NavItem("Conversor", modifier = Modifier.weight(1f), navController)
        NavItem("Aprender", modifier = Modifier.weight(1f), navController)
    }
}

@Composable
fun NavItem(label: String, modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { navController.navigate(label) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Image(
                when (label) {
                    "Perfil" -> FontAwesome.Icon.faw_user1
                    "Conversor" -> FontAwesome.Icon.faw_exchange_alt
                    else -> FontAwesome.Icon.faw_book
                },
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(25.dp)
            )

        Spacer(Modifier.height(8.dp))

        Text(
            fontSize = 15.sp,
            text = label,
            color = MaterialTheme.colorScheme.onSurface
        )

    }
}