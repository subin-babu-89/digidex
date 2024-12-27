package com.example.digidex.repository

import com.example.digidex.network.DigiApiService
import com.example.digidex.network.model.DigimonListPage
import javax.inject.Inject

class DigimonRepository @Inject constructor(private val digiApiService: DigiApiService) {
    suspend fun getDigimonList(pageNumber: Int): DigimonListPage? {
        return digiApiService.getDigimonList(pageNumber = pageNumber).body()
    }
}