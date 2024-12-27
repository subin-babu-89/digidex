package com.example.digidex.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.digidex.network.model.DigimonListPage
import com.example.digidex.ui.components.LoadingState
import com.example.digidex.viewmodels.DigimonListViewModel
import com.example.digidex.viewmodels.DigimonListViewState

@Composable
fun DigimonListScreen(
    modifier: Modifier = Modifier,
    viewModel: DigimonListViewModel = hiltViewModel(),
) {
    Column(modifier = modifier) {
        Row {
            Text(text = "Digimon list screen")
        }
        val viewState by viewModel.viewState.collectAsStateWithLifecycle()
        LaunchedEffect(Unit) {
            viewModel.getDigimonList()
        }
        when (val state = viewState) {
            is DigimonListViewState.Error -> {
                Text(modifier = Modifier.fillMaxWidth(), text = "Error: ${state.message}")
            }

            DigimonListViewState.Loading -> {
                LoadingState(modifier = Modifier.fillMaxWidth())
            }

            is DigimonListViewState.Success -> {
                DigimonList(digimons = state.digimonList)
            }
        }
    }
}

@Composable
fun DigimonList(
    modifier: Modifier = Modifier,
    digimons: List<DigimonListPage.DigimonListItem>,
) {
    val scrollState: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.onGloballyPositioned { },
        state = scrollState
    ) {
        items(items = digimons, key = { it.hashCode() }) {
            Text(text = it.name)
        }
    }
}