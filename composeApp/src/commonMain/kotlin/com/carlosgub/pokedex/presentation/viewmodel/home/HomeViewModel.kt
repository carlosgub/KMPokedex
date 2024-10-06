package com.carlosgub.pokedex.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : ViewModel(),
    ContainerHost<HomeScreenState, HomeScreenSideEffect>,
    HomeScreenIntents {

    override fun navigateToPokemonDetail(name: String): Job =
        intent {
            postSideEffect(HomeScreenSideEffect.NavigateToPokemonDetail)
        }

    override fun getPokemonList(): Job =
        intent {
            showLoading()
            setPokemonList(getPokemonListUseCase())
        }

    override val container: Container<HomeScreenState, HomeScreenSideEffect> =
        viewModelScope.container(HomeScreenState()) {
            getPokemonList()
        }

    fun setPokemonList(pokemonList: List<PokemonModel>): Job =
        intent {
            reduce {
                state.copy(
                    pokemonList = pokemonList,
                    showLoading = false,
                )
            }
        }

    private fun showLoading(): Job =
        intent {
            reduce {
                state.copy(
                    showLoading = true,
                    pokemonList = listOf(),
                )
            }
        }
}