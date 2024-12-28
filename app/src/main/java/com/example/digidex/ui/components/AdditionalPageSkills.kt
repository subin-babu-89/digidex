package com.example.digidex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digidex.network.model.DigimonDetails

@Composable
fun AdditionalPageSkills(modifier: Modifier = Modifier, skills: List<DigimonDetails.Skills>) {
    Column(modifier = modifier.padding(top = 4.dp)) {
        skills.forEach {
            Column(
                modifier = Modifier.fillMaxWidth().padding(4.dp).clip(
                    RoundedCornerShape(8.dp)
                ).background(color = MaterialTheme.colorScheme.onTertiary).padding( 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = it.skill,
                    style = TextStyle(fontSize = 32.sp, color = MaterialTheme.colorScheme.tertiary)
                )
                if (it.translation.isNotBlank()) Text(text = "(${it.translation})", style = TextStyle(fontStyle = FontStyle.Italic))
                Text(text = it.description)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview
@Composable
private fun AdditionalPageSkillsPreview() {
    AdditionalPageSkills(
        skills = listOf(
            DigimonDetails.Skills(
                id = 1,
                skill = "Holy Arrrow",
                translation = "sdfskjdf",
                description = "Forms a bow with the wings on its glove and shoots a holy arrow of lightning."
            )
        )
    )
}