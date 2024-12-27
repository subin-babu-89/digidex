package com.example.digidex.network

import com.example.digidex.network.model.DigimonDetails
import com.example.digidex.network.model.DigimonListPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigiApiService {
    @GET("digimon")
    suspend fun getDigimonList(
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<DigimonListPage>

    @GET("digimon/{id}")
    suspend fun getDigimonDetails(@Path("id") id: Int): Response<DigimonDetails>
}