package com.carlosgub.pokedex.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

    PokemonList(state.pokemonList)
}

@Composable
private fun PokemonList(list: List<PokemonModel>) {
    LazyColumn {
        items(list) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

@Composable
private fun PokemonItem(pokemon: PokemonModel) {
    Text(pokemon.name)
}