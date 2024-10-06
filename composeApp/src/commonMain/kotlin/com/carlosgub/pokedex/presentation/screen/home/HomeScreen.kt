@file:OptIn(ExperimentalMaterial3Api::class)

package com.carlosgub.pokedex.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.presentation.viewmodel.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    Box(
        modifier = modifier,
    ) {
        Scaffold(
            topBar = { HomeTopAppBar() },
            modifier = Modifier.background(Color.Transparent)
        ) {
            PokemonList(state.pokemonList)
        }
    }

}


@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Pokedex",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
    )
}

@Composable
private fun PokemonList(list: List<PokemonModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(list) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

@Composable
private fun PokemonItem(pokemon: PokemonModel) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.DarkGray
        )
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 18.dp,
                    )
            ) {
                Text(
                    text = pokemon.name.english.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                pokemon.type.forEach { type ->
                    Text(
                        text = type,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(
                                top = 8.dp
                            )
                            .clip(CircleShape)
                            .background(
                                Color.White.copy(
                                    alpha = 0.5f
                                )
                            )
                            .padding(
                                horizontal = 8.dp,
                                vertical = 6.dp
                            )
                    )
                }

            }
            PokemonImage(
                url = pokemon.image.thumbnail
            )
        }
    }
}

@Composable
private fun PokemonImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    coil3.compose.AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
    )
}

