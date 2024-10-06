package com.carlosgub.pokedex.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.domain.usecase.GetPokemonListUseCase
import com.carlosgub.pokedex.domain.usecase.SavePokemonListUseCase
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val savePokemonListUseCase: SavePokemonListUseCase,
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
            val pokemonList = getPokemonListUseCase()
            savePokemonListUseCase(pokemonList)
            setPokemonList(pokemonList)
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