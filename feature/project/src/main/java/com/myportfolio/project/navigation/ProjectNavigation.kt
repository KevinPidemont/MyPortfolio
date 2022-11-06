package com.myportfolio.project.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.myportfolio.project.ui.view.MyProjectScreen
import com.myportfolio.project.ui.view.ProjectDetailScreen

private const val ProjectBaseRouteName: String = "projects"
private const val ProjectDetailArgumentName: String = "project_id"
private const val ProjectDetailRouteName: String =
    "$ProjectBaseRouteName/{$ProjectDetailArgumentName}"

fun NavController.navigateToMyProjects(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ProjectBaseRouteName, builder = builder)
}

fun NavController.navigateToProjectDetail(projectId: Long) {
    navigate("$ProjectBaseRouteName/$projectId")
}

fun NavGraphBuilder.myProjectsScreen(onProjectSelected: (Long) -> Unit) {
    composable(ProjectBaseRouteName) {
        MyProjectScreen(onProjectSelected = onProjectSelected)
    }
}

fun NavGraphBuilder.projectDetailScreen() {
    composable(
        route = ProjectDetailRouteName,
        arguments = listOf(
            navArgument(ProjectDetailArgumentName) {
                type = NavType.LongType
            }
        )
    ) {
        ProjectDetailScreen(projectId = it.arguments?.getLong(ProjectDetailArgumentName))
    }
}