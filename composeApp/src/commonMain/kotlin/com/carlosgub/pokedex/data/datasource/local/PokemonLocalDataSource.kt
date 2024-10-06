package com.carlosgub.pokedex.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.carlosgub.pokedex.data.datasource.local.sqldelight.SharedDatabase
import com.carlosgub.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import pokemon.Pokemon

class PokemonLocalDataSource(
    private val sharedDatabase: SharedDatabase,
) {
    suspend fun getPokemonList(): Flow<List<Pokemon>> =
        sharedDatabase().pokemonQueries
            .getPokemonList()
            .asFlow()
            .mapToList(Dispatchers.IO)

    suspend fun savePokemonList(pokemonList: List<PokemonModel>) =
        pokemonList.forEach { pokemon ->
            try {
                sharedDatabase().pokemonQueries.insert(
                    id = pokemon.id.toLong(),
                    color = pokemon.color,
                    image = pokemon.image,
                    name = pokemon.name,
                    type = pokemon.type.toString()
                )
            } catch (ex: Exception) {
                // DO NOTHING
            }
        }
}