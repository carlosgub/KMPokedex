package com.carlosgub.pokedex.domain.repository

import com.carlosgub.pokedex.domain.model.PokemonModel

interface PokemonRepository {
    suspend fun getPokemonList(): List<PokemonModel>
    suspend fun savePokemonList(pokemonList: List<PokemonModel>)
}