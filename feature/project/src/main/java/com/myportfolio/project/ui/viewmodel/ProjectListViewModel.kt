package com.myportfolio.project.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.myportfolio.project.data.repository.ProjectRepositoryImpl
import com.myportfolio.project.domain.model.ProjectList
import com.myportfolio.project.domain.repository.ProjectRepository

// TODO use DI here
class ProjectListViewModel(private val repository: ProjectRepository = ProjectRepositoryImpl()) : ViewModel() {
    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState>
        get() = _uiState

    fun getProjectList() {
        _uiState.value = UiState(repository.getProjects())
    }
}

data class UiState(
    val projectList: ProjectList = emptyProjectList()
) {
    companion object {
        fun emptyProjectList() = ProjectList(listOf())
    }
}