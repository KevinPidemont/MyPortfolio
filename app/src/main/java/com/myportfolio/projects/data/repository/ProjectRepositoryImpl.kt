package com.myportfolio.projects.data.repository

import com.myportfolio.R
import com.myportfolio.projects.domain.model.Project
import com.myportfolio.projects.domain.model.ProjectList
import com.myportfolio.projects.domain.model.Tag
import com.myportfolio.projects.domain.repository.ProjectRepository

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
}