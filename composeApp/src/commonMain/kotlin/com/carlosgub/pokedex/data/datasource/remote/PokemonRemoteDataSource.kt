package com.carlosgub.pokedex.data.datasource.remote

import com.carlosgub.pokedex.data.datasource.remote.response.PokemonListResponse
import com.carlosgub.pokedex.domain.model.PokemonModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(): PokemonListResponse =
        try {
            httpClient.get("http://10.0.2.2:3000/api/pokedexlist")
                .body()
        }catch (ex:Exception){
            PokemonListResponse(listOf())
        }

}