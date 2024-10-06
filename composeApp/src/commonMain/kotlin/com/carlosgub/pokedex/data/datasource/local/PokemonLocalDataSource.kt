package com.carlosgub.pokedex.data.datasource.local

import com.carlosgub.pokedex.data.datasource.local.sqldelight.SharedDatabase
import com.carlosgub.pokedex.domain.model.PokemonModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pokemon.Pokemon

class PokemonLocalDataSource(
    private val sharedDatabase: SharedDatabase,
) {
    suspend fun getPokemonList(): List<Pokemon> =
        sharedDatabase().pokemonQueries
            .getPokemonList()
            .executeAsList()

    suspend fun savePokemonList(pokemonList: List<PokemonModel>) =
        pokemonList.forEach { pokemon ->
            try {
                sharedDatabase().pokemonQueries.insert(
                    id = pokemon.id.toLong(),
                    color = pokemon.color,
                    image = pokemon.image,
                    name = pokemon.name,
                    type = Json.encodeToString(pokemon.type)
                )
            } catch (ex: Exception) {
                // DO NOTHING
            }
        }
}