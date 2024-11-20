package com.carlosgub.pokedex.di

import com.carlosgub.pokedex.data.datasource.local.sqldelight.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module =
    module {
        single { DatabaseDriverFactory(get()) }
    }
