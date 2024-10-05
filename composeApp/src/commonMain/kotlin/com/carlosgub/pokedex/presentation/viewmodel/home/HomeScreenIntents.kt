package com.carlosgub.pokedex.presentation.viewmodel.home

import kotlinx.coroutines.Job

interface HomeScreenIntents {
    fun navigateToPokemonDetail(name: String): Job
    fun getPokemonList(): Job
}