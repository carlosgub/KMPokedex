package com.carlosgub.pokedex.data.datasource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val pokemonList: List<PokemonResponse>
)
