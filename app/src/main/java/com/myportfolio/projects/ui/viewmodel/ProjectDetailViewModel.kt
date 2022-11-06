package com.myportfolio.projects.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ProjectDetailState>(ProjectDetailState.Loading)
    val uiState: StateFlow<ProjectDetailState> = _uiState.asStateFlow()

    fun getProjectDetail(id: Long?) {
        _uiState.value = ProjectDetailState.Loading
        if (id == null) {
            _uiState.value = ProjectDetailState.Error("Oops, le projet est introuvable !")
        } else {
            _uiState.value = ProjectDetailState.Success(id)
        }
    }
}

sealed class ProjectDetailState {
    object Loading : ProjectDetailState()
    data class Success(val id: Long) : ProjectDetailState()
    data class Error(val message: String) : ProjectDetailState()
}