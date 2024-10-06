@file:OptIn(ExperimentalMaterial3Api::class)

package com.carlosgub.pokedex.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.presentation.viewmodel.home.HomeViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    Box(
        modifier = modifier,
    ) {
        Scaffold(
            topBar = { HomeTopAppBar() },
            modifier = Modifier.background(Color.Transparent)
        ) {
            PokemonList(state.pokemonList)
        }
    }

}


@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Pokedex",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
    )
}

@Composable
private fun PokemonList(list: List<PokemonModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
            )
        }
    }
}

@Composable
private fun PokemonItem(
    pokemon: PokemonModel,
    modifier: Modifier = Modifier
) {
    val containerColor = Color(pokemon.color)

    Card(
        modifier = modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            PokemonImage(
                url = pokemon.image,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            PokemonInformation(pokemon)
        }
    }
}

@Composable
private fun PokemonInformation(pokemon: PokemonModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 12.dp,
                vertical = 18.dp,
            )
    ) {
        Text(
            text = pokemon.name,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        pokemon.type.forEach { type ->
            Text(
                text = type,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(
                        top = 8.dp
                    )
                    .clip(CircleShape)
                    .background(
                        Color.White.copy(
                            alpha = 0.2f
                        )
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    )
            )
        }

    }
}

@Composable
private fun PokemonImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.padding(top = 60.dp),
        contentAlignment = Alignment.BottomEnd) {
        Image(
            painter = painterResource(Res.drawable.pokeball),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.White.copy(0.2f)),
            modifier = Modifier.size(80.dp)
        )
        KamelImage(
            resource = asyncPainterResource(url),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(
                    end = 8.dp,
                    bottom = 8.dp,
                )
        )
    }
}

