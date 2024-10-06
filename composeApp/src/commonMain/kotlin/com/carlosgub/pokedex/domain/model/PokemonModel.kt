package com.carlosgub.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    val id: String,
    val names: NameModel,
    val image: ImageModel,
    val typeList: List<String>? = listOf("Unknown")
) {
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
