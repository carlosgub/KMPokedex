package com.carlosgub.pokedex.data.repository

import com.carlosgub.pokedex.data.datasource.remote.PokemonRemoteDataSource
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {
    override suspend fun getPokemonList(): List<PokemonModel> =
        pokemonRemoteDataSource.getPokemonList().pokemonList

}