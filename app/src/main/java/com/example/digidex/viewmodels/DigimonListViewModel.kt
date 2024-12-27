package com.example.digidex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.network.model.DigimonListPage
import com.example.digidex.repository.DigimonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonListViewModel @Inject constructor(private val repository: DigimonRepository) :
    ViewModel() {
    private val _internalViewState =
        MutableStateFlow<DigimonListViewState>(DigimonListViewState.Loading)
    val viewState = _internalViewState.asStateFlow()

    fun getDigimonList() = viewModelScope.launch {
        _internalViewState.update { DigimonListViewState.Loading }
        val response = repository.getDigimonList(pageNumber = 0)
        if (response != null) {
            _internalViewState.update { DigimonListViewState.Success(digimonList = response.content) }
        } else {
            _internalViewState.update { DigimonListViewState.Error("Something went wrong") }
        }
    }
}

sealed interface DigimonListViewState {
    data object Loading : DigimonListViewState
    data class Success(val digimonList: List<DigimonListPage.DigimonListItem>) :
        DigimonListViewState

    data class Error(val message: String) : DigimonListViewState
}