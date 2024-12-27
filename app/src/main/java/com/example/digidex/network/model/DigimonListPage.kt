package com.example.digidex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DigimonListPage(
    @SerialName("content")
    val content: List<DigimonListItem>,
    @SerialName("pageable")
    val pageable: DigimonPagingData,
) {
    @Serializable
    data class DigimonListItem(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("href")
        val href: String,
        @SerialName("image")
        val image: String,
    )

    @Serializable
    data class DigimonPagingData(
        @SerialName("currentPage")
        val currentPage: Int,
        @SerialName("elementsOnPage")
        val elementsOnPage: Int,
        @SerialName("totalElements")
        val totalElements: Int,
        @SerialName("totalPages")
        val totalPages: Int,
        @SerialName("previousPage")
        val previousPage: String,
        @SerialName("nextPage")
        val nextPage: String,
    )
}
