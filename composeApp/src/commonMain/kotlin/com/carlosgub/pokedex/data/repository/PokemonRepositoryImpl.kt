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
    override suspend fun getPokemonList(): List<PokemonModel> {
        return if (true) {
            val pokemonList = pokemonLocalDataSource.getPokemonList()
            pokemonList.map { pokemon ->
                    pokemon.toPokemonModel()
                }
        } else {
            val pokemonList = pokemonRemoteDataSource.getPokemonList().pokemonList.map { pokemonResponse ->
                pokemonResponse.toPokemonModel()
            }
            pokemonLocalDataSource.savePokemonList(pokemonList)
            pokemonList
        }
    }

}