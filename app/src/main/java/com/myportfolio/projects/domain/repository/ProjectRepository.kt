package com.myportfolio.projects.domain.repository

import com.myportfolio.projects.domain.model.Project
import com.myportfolio.projects.domain.model.ProjectList

interface ProjectRepository {
    fun getProjects(): ProjectList

    fun getProjectById(id: Long): Project?
}