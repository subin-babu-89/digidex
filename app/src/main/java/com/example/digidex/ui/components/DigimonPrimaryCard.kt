package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.digidex.network.model.DigimonDetails

@Composable
fun DigimonPrimaryCard(
    modifier: Modifier = Modifier,
    id: Int,
    name: String,
    imageUrl: List<DigimonDetails.Images>,
    levels: List<DigimonDetails.Levels>,
    attributes: List<DigimonDetails.Attributes>,
    types: List<DigimonDetails.Types>,
    fields: List<DigimonDetails.Fields>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "$id")
        Text(
            text = name,
            style = TextStyle(fontSize = 24.sp, textDecoration = TextDecoration.Underline)
        )
        AsyncImage(
            model = imageUrl[0].href,
            contentDescription = "digimon-image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            PrimaryCardPropertyStack(title = "Level", dataPoints = levels.map { it.level })
            PrimaryCardPropertyStack(
                title = "Attribute",
                dataPoints = attributes.map { it.attribute })
            PrimaryCardPropertyStack(title = "Type", dataPoints = types.map { it.type })

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Fields")
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            fields.forEach {
                AsyncImage(
                    model = it.image,
                    contentDescription = "field-image",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun PrimaryCardPropertyStack(
    modifier: Modifier = Modifier,
    title: String,
    dataPoints: List<String>,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title)
        Spacer(modifier = Modifier.height(4.dp))
        dataPoints.forEach {
            Text(text = it)
        }
    }
}

@Preview
@Composable
private fun DigimonPrimaryCardPreview() {
    DigimonPrimaryCard(
        id = 299,
        name = "Digmon",
        imageUrl = listOf(
            DigimonDetails.Images(
                href = "https://digi-api.com/images/digimon/w/Digmon.png",
                transparent = false,
            )
        ),
        levels = listOf(
            DigimonDetails.Levels(
                id = 2,
                level = "Adult",
            ), DigimonDetails.Levels(
                id = 7,
                level = "Armor",
            ), DigimonDetails.Levels(
                id = 4,
                level = "Child",
            )
        ),
        attributes = listOf(
            DigimonDetails.Attributes(id = 4, attribute = "Vaccine"),
            DigimonDetails.Attributes(id = 2, attribute = "Free"),
        ),
        types = listOf(DigimonDetails.Types(id = 25, type = "Insect")),
        fields = listOf(
            DigimonDetails.Fields(
                id = 10,
                field = "Jungle Troopers",
                image = "https://digi-api.com/images/etc/fields/Jungle_Troopers.png"
            ),
            DigimonDetails.Fields(
                id = 2,
                field = "Nature Spirits",
                image = "https://digi-api.com/images/etc/fields/Nature_Spirits.png"
            ),
            DigimonDetails.Fields(
                id = 3,
                field = "Nightmare Soldiers",
                image = "https://digi-api.com/images/etc/fields/Nightmare_Soldiers.png"
            ),
        )
    )
}