package com.carlosgub.pokedex.data.datasource.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: NameResponse,
    val image: ImageResponse,
    val type: List<String>
){
    @Serializable
    data class NameResponse(
        val english: String,
        val japanese: String,
        val chinese: String,
        val french: String,
    )

    @Serializable
    data class ImageResponse(
        val thumbnail: String
    )
}
