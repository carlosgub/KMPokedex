package com.carlosgub.pokedex.data.datasource.remote

import com.carlosgub.pokedex.domain.model.PokemonListModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(): PokemonListModel {
        return httpClient.get("https://pokeapi.co/api/v2/pokemon").body()
    }
}