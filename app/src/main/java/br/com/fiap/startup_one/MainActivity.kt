package br.com.fiap.startup_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.startup_one.screens.HomeScreen
import br.com.fiap.startup_one.screens.PostProfileScreen
import br.com.fiap.startup_one.screens.SearchScreen
import androidx.compose.material3.*
import br.com.fiap.startup_one.screens.ProfileScreen
import br.com.fiap.startup_one.screens.ScheduleScreen
import br.com.fiap.startup_one.ui.theme.StartuponeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartuponeTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        builder = {
                            composable(route = "home") {
                                HomeScreen(navController)
                            }
                            composable(route = "search") {
                                SearchScreen(navController)
                            }
                            composable(
                                route = "profile/{postId}",
                                arguments = listOf(navArgument("postId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val postId = backStackEntry.arguments?.getLong("postId")
                                postId?.let {
                                    PostProfileScreen(navController = navController, postId = it)
                                }
                            }
                            composable(route = "schedule") {
                                ScheduleScreen(navController)
                            }
                            composable(route = "profileUser") {
                                ProfileScreen(navController)
                            }
                        }
                    )
                }
            }
        }
    }
}

