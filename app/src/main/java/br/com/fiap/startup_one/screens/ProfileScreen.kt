package br.com.fiap.startup_one.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Em manutenção")
                }
            }

        }
        FooterBackground {
            IconMenuOff(navController)
            IconSearchOff(navController)
            IconCalendarOff(navController)
            IconProfileOn()
        }
    }
}
