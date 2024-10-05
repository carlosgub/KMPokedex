package com.carlosgub.pokedex

import androidx.compose.ui.window.ComposeUIViewController
import com.carlosgub.pokedex.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }