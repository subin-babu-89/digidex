package com.example.digidex.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digidex.network.model.DigimonListPage
import javax.inject.Inject

private const val INITIAL_PAGE_INDEX = 0

class DigimonPagingSource @Inject constructor(private val apiService: DigiApiService) :
    PagingSource<Int, DigimonListPage.DigimonListItem>() {
    override fun getRefreshKey(state: PagingState<Int, DigimonListPage.DigimonListItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DigimonListPage.DigimonListItem> {
        val currentPage = params.key ?: INITIAL_PAGE_INDEX
        try {
            val response =
                apiService.getDigimonList(pageNumber = currentPage, pageSize = params.loadSize)
                    .body()
            val digimonList = response?.content
            val paginationDetails = response?.pageable
            return LoadResult.Page(
                data = digimonList ?: emptyList(),
                prevKey = paginationDetails?.getPreviousPageToken,
                nextKey = paginationDetails?.getNextPageToken,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}