package com.plcoding.jetpackcomposepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint

const val POKEMON_LIST_SCREEN = "POKEMON_LIST_SCREEN"
const val POKEMON_DETAILS_SCREEN = "POKEMON_DETAILS_SCREEN"

const val DOMINANT_COLOR_ARG = "DOMINANT_COLOR_ARG"
const val POKEMON_NAME_ARG = "POKEMON_NAME_ARG"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = POKEMON_LIST_SCREEN
                ) {
                    composable(POKEMON_LIST_SCREEN) {

                    }
                    composable(
                        "$POKEMON_DETAILS_SCREEN/$DOMINANT_COLOR_ARG/$POKEMON_NAME_ARG",
                        arguments = listOf(
                            navArgument(
                                DOMINANT_COLOR_ARG
                            ) {
                                type = NavType.IntType
                            },
                            navArgument(POKEMON_NAME_ARG) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(DOMINANT_COLOR_ARG)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it?.arguments?.getString(POKEMON_NAME_ARG)
                        }
                    }
                }
            }
        }
    }
}
