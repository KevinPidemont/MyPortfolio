package com.myportfolio.projects.domain.repository

import com.myportfolio.projects.domain.model.ProjectList

interface ProjectRepository {
    fun getProjects(): ProjectList
}