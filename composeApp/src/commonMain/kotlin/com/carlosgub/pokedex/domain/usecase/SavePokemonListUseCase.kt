package com.carlosgub.pokedex.domain.usecase

import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SavePokemonListUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonList: List<PokemonModel>) =
        withContext(Dispatchers.IO) {
            pokemonRepository.savePokemonList(pokemonList)
        }

}
