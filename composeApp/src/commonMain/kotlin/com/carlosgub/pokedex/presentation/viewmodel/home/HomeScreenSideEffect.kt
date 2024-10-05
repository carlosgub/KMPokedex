package com.carlosgub.pokedex.presentation.viewmodel.home

sealed class HomeScreenSideEffect {
    data object NavigateToPokemonDetail : HomeScreenSideEffect()
}