package com.example.digidex.repository

import com.example.digidex.network.DigiApiService
import com.example.digidex.network.model.DigimonListPage
import javax.inject.Inject

private const val PAGE_SIZE = 30

class DigimonRepository @Inject constructor(private val digiApiService: DigiApiService) {
    suspend fun getDigimonList(pageNumber: Int): DigimonListPage? {
        return digiApiService.getDigimonList(
            pageNumber = pageNumber,
            pageSize = PAGE_SIZE
        ).body()
    }
}