package br.com.fiap.startup_one.screens

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.startup_one.database.repository.getAllPosts


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()){
        HeaderBackground{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(18.dp))

                IconPlus()

                Spacer(modifier = Modifier.width(18.dp))

                SearchButton(navController)

                Spacer(modifier = Modifier.width(18.dp))

                IconMenu()
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        TextCarrossel()
        Carrossel()

        Spacer(modifier = Modifier.height(5.dp))

        Column(modifier = Modifier.weight(1f)) {
            val posts = getAllPosts()
            PostsCard(navController, posts)
            Spacer(modifier = Modifier.height(20.dp))

        }

        FooterBackground {

            IconMenuON()

            IconSearchOff(navController)

            IconCalendarOff(navController)

            IconProfileOff(navController)
        }
    }

}

