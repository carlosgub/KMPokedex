package com.carlosgub.pokedex.di

import com.carlosgub.pokedex.core.network.HttpClient
import com.carlosgub.pokedex.data.datasource.remote.PokemonRemoteDataSource
import com.carlosgub.pokedex.data.repository.PokemonRepositoryImpl
import com.carlosgub.pokedex.domain.repository.PokemonRepository
import com.carlosgub.pokedex.domain.usecase.GetPokemonListUseCase
import com.carlosgub.pokedex.presentation.viewmodel.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::HomeViewModel)

    factory {
        GetPokemonListUseCase(
            pokemonRepository = get()
        )
    }

    factory<PokemonRepository> {
        PokemonRepositoryImpl(
            pokemonRemoteDataSource = get()
        )
    }

    factory {
        PokemonRemoteDataSource(
            httpClient = get()
        )
    }

}

val networkModule = module {
    single {
        HttpClient.httpClient
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            appModule,
            networkModule
        )
    }

fun initKoin() = initKoin {}