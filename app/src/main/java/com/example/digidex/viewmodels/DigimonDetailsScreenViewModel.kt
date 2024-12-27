package com.example.digidex.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.network.model.DigimonDetails
import com.example.digidex.repository.DigimonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonDetailsScreenViewModel @Inject constructor(private val repository: DigimonRepository) :
    ViewModel() {
    private val _internalViewState =
        MutableStateFlow<DigimonDetailsScreenState>(DigimonDetailsScreenState.Loading)
    val viewState = _internalViewState.asStateFlow()

    fun getDigimonDetails(id: Int) = viewModelScope.launch {
        _internalViewState.update { DigimonDetailsScreenState.Loading }
        val response = repository.getDigimonDetails(id)
        if (response != null) {
            _internalViewState.update { DigimonDetailsScreenState.Success(response) }
        } else {
            _internalViewState.update { DigimonDetailsScreenState.Error("Error fetching digimon details") }
        }
    }
}

sealed class DigimonDetailsScreenState {
    data object Loading : DigimonDetailsScreenState()
    data class Error(val message: String) : DigimonDetailsScreenState()
    data class Success(val digimonDetails: DigimonDetails) : DigimonDetailsScreenState()

}