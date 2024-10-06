@file:OptIn(ExperimentalMaterial3Api::class)

package com.carlosgub.pokedex.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosgub.pokedex.domain.model.PokemonModel
import com.carlosgub.pokedex.presentation.viewmodel.home.HomeViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.viewmodel.koinViewModel

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
    var desiredItemMinHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                modifier = Modifier
                    .onPlaced {
                        with(density) {
                            if (desiredItemMinHeight < it.size.height.toDp()) {
                                desiredItemMinHeight = it.size.height.toDp()
                            }
                        }
                    }
                    .defaultMinSize(minHeight = desiredItemMinHeight),
            )
        }
    }
}

@Composable
private fun PokemonItem(
    pokemon: PokemonModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth().wrapContentHeight()
            .padding(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.DarkGray
        )
    ) {
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            PokemonImage(
                url = pokemon.image.thumbnail,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 18.dp,
                    )
            ) {
                Text(
                    text = pokemon.name.english,
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
                                    alpha = 0.5f
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
    }
}

@Composable
private fun PokemonImage(
    url: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.padding(top = 40.dp)) {
        KamelImage(
            resource = asyncPainterResource(url),
            contentDescription = null,
            modifier = modifier
                .padding(
                    end = 12.dp,
                    bottom = 18.dp,
                )
                .size(80.dp)
        )
    }
}

