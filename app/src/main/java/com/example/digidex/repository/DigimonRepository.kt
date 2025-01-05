package com.example.digidex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.digidex.network.DigiApiService
import com.example.digidex.network.DigimonPagingSource
import com.example.digidex.network.model.DigimonDetails
import com.example.digidex.network.model.DigimonListPage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 30

class DigimonRepository @Inject constructor(private val digiApiService: DigiApiService) {
    suspend fun getDigimonDetails(id: Int): DigimonDetails? {
        return digiApiService.getDigimonDetails(id = id).body()
    }

    fun getPagedDigimonList(): Flow<PagingData<DigimonListPage.DigimonListItem>> {
        return Pager(
            pagingSourceFactory = { DigimonPagingSource(digiApiService) },
            config = PagingConfig(pageSize = PAGE_SIZE)
        ).flow
    }
}