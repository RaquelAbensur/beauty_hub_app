package br.com.fiap.startup_one.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.startup_one.database.repository.getAllMonth

@Composable
fun ScheduleScreen(navController: NavController) {
    val scheduleList = getAllMonth()
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(35.dp))
        Column(modifier = Modifier.weight(1f)) {
            Schedule(scheduleList, isSelected = true)
        }
        FooterBackground {
            IconMenuOff(navController)
            IconSearchOff(navController)
            IconCalendarOn()
            IconProfileOff(navController)
        }
    }


}