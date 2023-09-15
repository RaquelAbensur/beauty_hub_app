package br.com.fiap.startup_one.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.startup_one.database.repository.getAllPosts
import br.com.fiap.startup_one.database.repository.getPostsByCategory

@Composable
fun SearchScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("") }

    Column {
        HeaderBackground {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(18.dp))

                IconPlus()

                Spacer(modifier = Modifier.width(18.dp))

                Search()

                Spacer(modifier = Modifier.width(18.dp))

                IconMenu()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))



        Column(modifier = Modifier.weight(1f)) {
            val posts = getAllPosts()
            CarrosselCategory(postsList = posts, onCategorySelected = { category ->
                selectedCategory = category
            }, navController = navController)
            if (selectedCategory.isNotEmpty()) {
                getPostsByCategory(posts, selectedCategory)
            } else {
                posts
            }
            Spacer(modifier = Modifier.height(20.dp))
        }


        FooterBackground {

            IconMenuOff(navController)

            IconSearchOn()

            IconCalendarOff(navController)

            IconProfileOff(navController)
        }
    }
}


