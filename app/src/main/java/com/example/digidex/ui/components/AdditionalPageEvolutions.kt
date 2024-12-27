package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.digidex.network.model.DigimonDetails

@Composable
fun AdditionalPageEvolutions(
    modifier: Modifier = Modifier,
    priorEvolutions: List<DigimonDetails.Evolution>,
    nextEvolutions: List<DigimonDetails.Evolution>,
) {
    Column(modifier = modifier) {
        EvolutionGrid(
            evolutions = priorEvolutions,
            evolutionType = "Prior Evolution",
            modifier = Modifier.fillMaxWidth()
        )
        EvolutionGrid(
            evolutions = nextEvolutions,
            evolutionType = "Next Evolution",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun AdditionalPageEvolutionsPreview() {
    AdditionalPageEvolutions(priorEvolutions = emptyList(), nextEvolutions = emptyList())
}

@Composable
fun EvolutionGrid(
    modifier: Modifier = Modifier,
    evolutionType: String,
    evolutions: List<DigimonDetails.Evolution>,
) {
    Column(modifier = modifier) {
            Text(text = evolutionType)
         Spacer(modifier = Modifier.height(16.dp))
        evolutions.forEach {   evolution ->
            EvolutionListItem(evolution = evolution)
        }
    }
}

@Composable
fun EvolutionListItem(modifier: Modifier = Modifier, evolution: DigimonDetails.Evolution) {
    Card(modifier = modifier) {
        AsyncImage(
            model = evolution.image,
            contentDescription = "evo-image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(text = "${evolution.id}")
        Text(text = evolution.digimon)
        if (evolution.condition.isNotBlank()) Text(text = "Condition: ${evolution.condition}")
    }
}

@Preview
@Composable
private fun EvolutionListItemPreview() {
    EvolutionListItem(
        evolution = DigimonDetails.Evolution(
            id = 0,
            digimon = "Armadimon",
            condition = "with or without the Digimental of Knowledge",
            image = "https://digi-api.com/images/digimon/w/Armadimon.png",
            url = "https://digi-api.com/api/v1/digimon/271"
        )
    )
}
