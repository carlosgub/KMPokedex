package com.carlosgub.pokedex.data.datasource.remote

import com.carlosgub.pokedex.domain.model.PokemonListModel
import com.carlosgub.pokedex.domain.model.PokemonModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(): PokemonListModel =
        httpClient.get("https://raw.githubusercontent.com/carlosgub/KMPokedex/refs/heads/main/json/PokedexList.json").body()

}