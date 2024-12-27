package com.example.digidex.network

import com.example.digidex.network.model.DigimonListPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DigiApiService {
    @GET("digimon")
    suspend fun getDigimonList(@Query("pageNumber") pageNumber: Int): Response<DigimonListPage>
}