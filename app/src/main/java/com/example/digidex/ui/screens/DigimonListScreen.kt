package com.example.digidex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.digidex.network.model.DigimonListPage
import com.example.digidex.ui.components.LoadingState
import com.example.digidex.viewmodels.DigimonListViewModel
import com.example.digidex.viewmodels.DigimonListViewState

@Composable
fun DigimonListScreen(
    modifier: Modifier = Modifier,
    viewModel: DigimonListViewModel = hiltViewModel(),
    onClick: (DigimonListPage.DigimonListItem) -> Unit,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getDigimonList()
    }
    when (val state = viewState) {
        is DigimonListViewState.Error -> {
            Text(modifier = Modifier.fillMaxWidth(), text = "Error: ${state.message}")
        }

        DigimonListViewState.Loading -> {
            LoadingState(modifier = Modifier.fillMaxSize())
        }

        is DigimonListViewState.Success -> {
            DigimonList(
                digimons = state.digimonList,
                modifier = modifier,
            ) {
                onClick(it)
            }
        }
    }
}

@Composable
fun DigimonList(
    modifier: Modifier = Modifier,
    digimons: List<DigimonListPage.DigimonListItem>,
    onClick: (DigimonListPage.DigimonListItem) -> Unit,
) {
    val scrollState: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primaryContainer),
        contentPadding = PaddingValues(8.dp),
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = digimons, key = { it.hashCode() }) {
            DigimonListItem(digimon = it, modifier = Modifier.fillMaxWidth()) {
                onClick(it)
            }
        }
    }
}

@Composable
fun DigimonListItem(
    modifier: Modifier = Modifier,
    digimon: DigimonListPage.DigimonListItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(
                CardDefaults.shape
            )
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
            .clickable { onClick() },
    ) {
        Row {
            AsyncImage(
                model = digimon.image,
                contentDescription = "digimon-image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(96.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = digimon.name,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp
                    )
                )
                Text(text = "${digimon.id}")
            }
        }
    }
}

@Preview
@Composable
private fun DigimonListItemPreview() {
    DigimonListItem(
        digimon = DigimonListPage.DigimonListItem(
            id = 1,
            name = "Agumon",
            href = "https://digi-api.com/api/v1/digimon/1",
            image = "https://digi-api.com/images/digimon/w/Agumon.png"
        )
    ){}
}