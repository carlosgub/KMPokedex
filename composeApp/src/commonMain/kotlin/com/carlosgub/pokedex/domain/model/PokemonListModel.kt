package com.carlosgub.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListModel(
    val pokemonList:List<PokemonModel>
)
