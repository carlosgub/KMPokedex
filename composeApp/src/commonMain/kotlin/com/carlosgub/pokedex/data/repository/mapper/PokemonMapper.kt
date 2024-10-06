package com.carlosgub.pokedex.data.repository.mapper

import com.carlosgub.pokedex.data.datasource.remote.response.PokemonResponse
import com.carlosgub.pokedex.domain.model.PokemonModel

fun PokemonResponse.toPokemonModel() = PokemonModel(
    id = this.id,
    name = this.name.english,
    color = this.getColor(),
    image = this.image.thumbnail,
    type = this.type
)

private fun PokemonResponse.getColor(): Long =
    when (this.type.first()) {
        "Grass" -> 0xFF77CC55
        "Fire" -> 0xFFfF4422
        "Water" -> 0xFF3399fF
        "Bug" -> 0xFFaAbB22
        "Normal" -> 0xFFAaAa99
        "Dark" -> 0xFF775544
        "Poison" -> 0xFFaA5599
        "Electric" -> 0xFffFcC33
        "Ground" -> 0xFFdDbB55
        "Fairy" -> 0XFFEE99EE
        "Fighting" -> 0XFFBB5545
        "Psychic" -> 0XFFFF5599
        "Rock" -> 0XFFBBAA66
        "Ghost" -> 0XFF6666BB
        "Ice" -> 0XFF66CCFF
        "Dragon" -> 0XFF7766EE

        else -> 0xFF444444
    }