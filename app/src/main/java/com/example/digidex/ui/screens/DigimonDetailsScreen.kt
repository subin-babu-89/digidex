package com.example.digidex.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.digidex.network.model.DigimonDetails
import com.example.digidex.ui.components.AdditionalDetails
import com.example.digidex.ui.components.AdditionalDetailsPage
import com.example.digidex.ui.components.AdditionalPageEvolutions
import com.example.digidex.ui.components.AdditionalPageInfo
import com.example.digidex.ui.components.AdditionalPageSkills
import com.example.digidex.ui.components.DigimonPrimaryCard
import com.example.digidex.ui.components.LoadingState
import com.example.digidex.viewmodels.DigimonDetailsScreenState
import com.example.digidex.viewmodels.DigimonDetailsScreenViewModel

@Composable
fun DigimonDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DigimonDetailsScreenViewModel = hiltViewModel(),
    id: Int,
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
            )
        }
    }
}

@Composable
fun DigimonDetailList(modifier: Modifier = Modifier, digimonDetails: DigimonDetails) {
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
        item {
            AdditionalDetails(
                subPages = listOf(
                    AdditionalDetailsPage("Info") {
                        AdditionalPageInfo(
                            releaseData = digimonDetails.releaseDate,
                            descriptions = digimonDetails.descriptions,
                        )
                    },
                    AdditionalDetailsPage("Skills") {
                        AdditionalPageSkills(skills = digimonDetails.skills)
                    },
                    AdditionalDetailsPage ("Evolutions") {
                        AdditionalPageEvolutions(
                            priorEvolutions = digimonDetails.priorEvolutions,
                            nextEvolutions = digimonDetails.nextEvolutions,
                        )
                    }
                )
            )
        }
    }
}

