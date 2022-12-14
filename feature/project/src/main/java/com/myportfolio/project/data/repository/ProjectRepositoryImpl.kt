package com.myportfolio.project.data.repository

import com.myportfolio.theme.R
import com.myportfolio.project.domain.model.Project
import com.myportfolio.project.domain.model.ProjectList
import com.myportfolio.project.domain.model.Tag
import com.myportfolio.project.domain.repository.ProjectRepository

private val ProjectsData = ProjectList(
    projects = listOf(
        Project(
            id = 1L,
            title = "MyPortfolio",
            image = R.drawable.my_profile_image,
            tags = listOf(
                Tag("Android"),
                Tag("Kotlin"),
                Tag("Jetpack Compose")
            )
        ),
        Project(
            id = 2L,
            title = "ShoppingList",
            image = R.drawable.flutter_logo,
            tags = listOf(
                Tag("Dart"),
                Tag("Flutter"),
                Tag("BLoC")
            )
        )
    )
)

class ProjectRepositoryImpl : ProjectRepository {
    override fun getProjects(): ProjectList {
        return ProjectsData
    }

    override fun getProjectById(id: Long): Project? {
        return ProjectsData.projects.find { it.id == id }
    }
}