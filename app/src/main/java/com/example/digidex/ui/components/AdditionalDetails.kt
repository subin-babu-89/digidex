package com.example.digidex.ui.components

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalDetailsPage(val title: String, val content: @Composable () -> Unit)
