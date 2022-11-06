package com.myportfolio.project.domain.repository

import com.myportfolio.project.domain.model.Project
import com.myportfolio.project.domain.model.ProjectList

interface ProjectRepository {
    fun getProjects(): ProjectList

    fun getProjectById(id: Long): Project?
}