package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.digidex.network.model.DigimonDetails

@Composable
fun AdditionalPageSkills(modifier: Modifier = Modifier, skills: List<DigimonDetails.Skills>) {
    Column {
        skills.forEach {
            Text(text = it.skill)
            Text(text = it.description)
        }
    }
}