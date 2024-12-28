package com.example.digidex.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.digidex.network.model.DigimonDetails
import com.example.digidex.ui.components.AdditionalDetailsPage
import com.example.digidex.ui.components.AdditionalPageEvolutions
import com.example.digidex.ui.components.AdditionalPageInfo
import com.example.digidex.ui.components.AdditionalPageSkills
import com.example.digidex.ui.components.DigimonPrimaryCard
import com.example.digidex.ui.components.LoadingState
import com.example.digidex.viewmodels.DigimonDetailsScreenState
import com.example.digidex.viewmodels.DigimonDetailsScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun DigimonDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DigimonDetailsScreenViewModel = hiltViewModel(),
    id: Int,
    onDigimonClick: (Int) -> Unit,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDigimonDetails(id = id)
    }

    when (val state = viewState) {
        is DigimonDetailsScreenState.Error -> {
            Text(modifier = Modifier.fillMaxWidth(), text = "Error: ${state.message}")
        }

        DigimonDetailsScreenState.Loading -> {
            LoadingState(modifier = Modifier.fillMaxSize())
        }

        is DigimonDetailsScreenState.Success -> {
            DigimonDetailList(
                digimonDetails = state.digimonDetails,
                modifier = modifier,
                onDigimonClick = onDigimonClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DigimonDetailList(
    modifier: Modifier = Modifier, digimonDetails: DigimonDetails,
    onDigimonClick: (Int) -> Unit,
) {
    val subPages = listOf(
        AdditionalDetailsPage("Info") {
            AdditionalPageInfo(
                releaseData = digimonDetails.releaseDate,
                descriptions = digimonDetails.descriptions,
            )
        },
        AdditionalDetailsPage("Skills") {
            AdditionalPageSkills(skills = digimonDetails.skills)
        },
        AdditionalDetailsPage("Evolutions") {
            AdditionalPageEvolutions(
                priorEvolutions = digimonDetails.priorEvolutions,
                nextEvolutions = digimonDetails.nextEvolutions,
                onDigimonClick = onDigimonClick
            )
        }
    )
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { subPages.size })
    val selectedTabIndex by remember { derivedStateOf { pagerState.currentPage } }
    LazyColumn(modifier = modifier) {
        item {
            DigimonPrimaryCard(
                id = digimonDetails.id,
                name = digimonDetails.name,
                imageUrl = digimonDetails.images,
                levels = digimonDetails.levels,
                attributes = digimonDetails.attributes,
                types = digimonDetails.types,
                fields = digimonDetails.fields,
            )
        }
        stickyHeader {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                subPages.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        unselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(subPages.indexOf(currentTab))
                            }
                        }
                    ) {
                        Text(text = currentTab.title, modifier = Modifier.padding(vertical = 16.dp))
                    }
                }
            }
        }
        item {
            HorizontalPager(state = pagerState) { page ->
                subPages[page].content()
            }
        }
    }
}

