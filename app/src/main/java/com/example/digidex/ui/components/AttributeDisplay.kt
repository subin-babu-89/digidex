package com.example.digidex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AttributeDisplay(modifier: Modifier = Modifier, label: String, value: String) {
    Row(modifier = modifier) {
        Text(
            text = "$label: $value",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start
            )

        )
    }
}

@Preview(device = "id:pixel_5", showBackground = true)
@Composable
private fun AttributeDisplayPreview() {
    AttributeDisplay(label = "origin", value = "reference_book")
}