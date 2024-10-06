@file:OptIn(ExperimentalCoilApi::class)

package com.carlosgub.pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.carlosgub.pokedex.presentation.navigation.PokedexScreen
import com.carlosgub.pokedex.presentation.screen.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    MaterialTheme {

        NavHost(
            navController = navController,
            startDestination = PokedexScreen.Home.name,
        ) {
            composable(route = PokedexScreen.Home.name) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()