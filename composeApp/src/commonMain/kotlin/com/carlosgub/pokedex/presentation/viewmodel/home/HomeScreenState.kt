package com.carlosgub.pokedex.presentation.viewmodel.home

import com.carlosgub.pokedex.domain.model.PokemonModel

data class HomeScreenState(
    val pokemonList: List<PokemonModel> = listOf(),
    val showLoading: Boolean = false
)
