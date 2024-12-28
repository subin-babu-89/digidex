package com.example.digidex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.digidex.network.model.DigimonDetails

@Composable
fun AdditionalPageEvolutions(
    modifier: Modifier = Modifier,
    priorEvolutions: List<DigimonDetails.Evolution>,
    nextEvolutions: List<DigimonDetails.Evolution>,
    onDigimonClick: (Int) -> Unit,
) {
    Column(modifier = modifier) {
        EvolutionGrid(
            evolutions = priorEvolutions,
            evolutionType = "Prior Evolution",
            modifier = Modifier.fillMaxWidth(),
            onDigimonClick = onDigimonClick
        )
        EvolutionGrid(
            evolutions = nextEvolutions,
            evolutionType = "Next Evolution",
            modifier = Modifier.fillMaxWidth(),
            onDigimonClick = onDigimonClick
        )
    }
}

@Preview
@Composable
private fun AdditionalPageEvolutionsPreview() {
    AdditionalPageEvolutions(priorEvolutions = emptyList(), nextEvolutions = emptyList()) {}
}


@Composable
fun EvolutionGrid(
    modifier: Modifier = Modifier,
    evolutionType: String,
    evolutions: List<DigimonDetails.Evolution>,
    rowCount: Int = 3,
    onDigimonClick: (Int) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = evolutionType,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        (0..evolutions.size / rowCount).forEach { index ->
            EvolutionListItem(
                evolutions = buildList {
                    for (i in 0 until rowCount) {
                        if (index * rowCount + i < evolutions.size) {
                            add(evolutions[index * rowCount + i])
                        }
                    }
                },
                rowCount = rowCount,
                onDigimonClick = onDigimonClick
            )
        }
    }
}

@Composable
fun EvolutionListItem(
    modifier: Modifier = Modifier,
    evolutions: List<DigimonDetails.Evolution>,
    rowCount: Int,
    onDigimonClick: (Int) -> Unit,
) {
    Row {
        evolutions.forEach { evolution1 ->
            Card(
                modifier = modifier
                    .weight(1f)
                    .padding(16.dp)
                    .clickable {
                        onDigimonClick(evolution1.id)
                    }
            ) {
                AsyncImage(
                    model = evolution1.image,
                    contentDescription = "evo-image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "${evolution1.id}",
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                )
                Text(
                    text = evolution1.digimon,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                )
            }
        }
        for (i in evolutions.size until rowCount) {
            Card(modifier = Modifier.weight(1f)) { }
        }
    }
}

@Preview
@Composable
private fun EvolutionListItemPreview() {
    EvolutionListItem(
        evolutions = listOf(
            DigimonDetails.Evolution(
                id = 0,
                digimon = "Armadimon",
                condition = "with or without the Digimental of Knowledge",
                image = "https://digi-api.com/images/digimon/w/Armadimon.png",
                url = "https://digi-api.com/api/v1/digimon/271"
            ),
            DigimonDetails.Evolution(
                id = 0,
                digimon = "Armadimon",
                condition = "with or without the Digimental of Knowledge",
                image = "https://digi-api.com/images/digimon/w/Armadimon.png",
                url = "https://digi-api.com/api/v1/digimon/271"
            ),
        ),
        rowCount = 2,
        onDigimonClick = {}
    )
}
