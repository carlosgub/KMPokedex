package com.carlosgub.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    val id: Int,
    val name: NameModel,
    val image: ImageModel,
    val type: List<String>
){
    @Serializable
    data class NameModel(
        val english: String,
        val japanese: String,
        val chinese: String,
        val french: String,
    )

    @Serializable
    data class ImageModel(
        val thumbnail: String
    )
}
