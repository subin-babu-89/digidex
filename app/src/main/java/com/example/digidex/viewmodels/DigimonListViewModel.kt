package com.example.digidex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.digidex.network.model.DigimonListPage
import com.example.digidex.repository.DigimonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonListViewModel @Inject constructor(private val repository: DigimonRepository) :
    ViewModel() {
    private val _internalViewState =
        MutableStateFlow<PagingData<DigimonListPage.DigimonListItem>>(PagingData.empty())
    val viewState = _internalViewState.asStateFlow()

    fun getDigimonList() = viewModelScope.launch {
        repository.getPagedDigimonList().collect {
            _internalViewState.value = it
        }
    }
}
