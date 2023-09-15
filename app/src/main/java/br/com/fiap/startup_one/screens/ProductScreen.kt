package br.com.fiap.startup_one.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.startup_one.R
import br.com.fiap.startup_one.database.repository.getAllPosts
import br.com.fiap.startup_one.model.ImagesCarrossel
import br.com.fiap.startup_one.model.Posts

@Composable
fun PostProfileScreen(navController: NavController, postId: Long) {

    val post = getAllPosts().find { it.id == postId }

    Column(modifier = Modifier.fillMaxSize()) {

        if (post != null) {
            HeaderStudioProfile(post)
        } else {

        }

        Column(modifier = Modifier.weight(1f)) {
            post?.let {
                CarrosselSpecialtiesItem(post = post)
                Spacer(modifier = Modifier.height(10.dp))
                PostProductItem(navController, post = post)
            }
        }

        FooterBackground {
            IconMenuOff(navController)
            IconSearchOn()
            IconCalendarOff(navController)
            IconProfileOff(navController)
        }
    }
}
