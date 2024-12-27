package com.example.digidex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DigimonDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("xAntibody")
    val xAntibody: Boolean,
    @SerialName("images")
    val images: List<Images>,
    @SerialName("levels")
    val levels: List<Levels>,
    @SerialName("types")
    val types: List<Types>,
    @SerialName("attributes")
    val attributes: List<Attributes>,
    @SerialName("fields")
    val fields: List<Fields>,
    @SerialName("releaseDate")
    val releaseDate: String,
    @SerialName("descriptions")
    val descriptions: List<Descriptions>,
    @SerialName("skills")
    val skills: List<Skills>,
    @SerialName("priorEvolutions")
    val priorEvolutions: List<Evolution>,
    @SerialName("nextEvolutions")
    val nextEvolutions: List<Evolution>,
) {
    @Serializable
    data class Images(
        @SerialName("href")
        val href: String,
        @SerialName("transparent")
        val transparent: Boolean,
    )

    @Serializable
    data class Levels(
        @SerialName("id")
        val id: Int,
        @SerialName("level")
        val level: String,
    )

    @Serializable
    data class Types(
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String,
    )

    @Serializable
    data class Attributes(
        @SerialName("id")
        val id: Int,
        @SerialName("attribute")
        val attribute: String,
    )

    @Serializable
    data class Fields(
        @SerialName("id")
        val id: Int,
        @SerialName("field")
        val field: String,
        @SerialName("image")
        val image: String,
    )

    @Serializable
    data class Descriptions(
        @SerialName("origin")
        val origin: String,
        @SerialName("language")
        val language: String,
        @SerialName("description")
        val description: String,
    )

    @Serializable
    data class Skills(
        @SerialName("id")
        val id: Int,
        @SerialName("skill")
        val skill: String,
        @SerialName("translation")
        val translation: String,
        @SerialName("description")
        val description: String,
    )


    @Serializable
    data class Evolution(
        @SerialName("id")
        val id: Int,
        @SerialName("digimon")
        val digimon: String,
        @SerialName("condition")
        val condition: String,
        @SerialName("image")
        val image: String,
        @SerialName("url")
        val url: String,
    )
}
