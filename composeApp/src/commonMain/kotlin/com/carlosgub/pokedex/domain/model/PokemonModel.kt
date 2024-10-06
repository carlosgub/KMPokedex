package com.carlosgub.pokedex.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    val id: Int,
    val name: String,
    val image: String,
    val type: List<String>,
    val color: Long,
)
