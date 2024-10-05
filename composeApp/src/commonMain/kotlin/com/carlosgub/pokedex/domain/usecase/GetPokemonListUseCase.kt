package com.carlosgub.pokedex.domain.usecase

import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.repository.PokemonRepository

class GetPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): List<PokemonModel> =
        pokemonRepository.getPokemonList()
}
