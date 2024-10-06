package com.carlosgub.pokedex.data.repository

import com.carlosgub.pokedex.data.datasource.local.PokemonLocalDataSource
import com.carlosgub.pokedex.data.datasource.remote.PokemonRemoteDataSource
import com.carlosgub.pokedex.data.repository.mapper.toPokemonModel
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
) : PokemonRepository {
    override suspend fun getPokemonList(): List<PokemonModel> =
        pokemonRemoteDataSource.getPokemonList().pokemonList.map { pokemonResponse ->
            pokemonResponse.toPokemonModel()
        }

    override suspend fun savePokemonList(pokemonList: List<PokemonModel>) =
        pokemonLocalDataSource.savePokemonList(pokemonList)

}