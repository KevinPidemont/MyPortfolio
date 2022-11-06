package com.myportfolio.experience.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myportfolio.experience.view.ExperienceDetailScreen
import com.myportfolio.experience.view.MyExperiencesScreen


private const val ExperiencesBaseRouteName = "experiences"
private const val ExperienceIdArgumentName = "experience_id"
private const val ExperienceDetailRouteName = "$ExperiencesBaseRouteName/{$ExperienceIdArgumentName}"

fun NavController.navigateToMyExperiences() {
    navigate(ExperiencesBaseRouteName)
}

fun NavGraphBuilder.myExperiencesScreen(onExperienceSelected: (Long) -> Unit) {
    composable(ExperiencesBaseRouteName) {
        MyExperiencesScreen(onExperienceSelected = onExperienceSelected)
    }
}

fun NavController.navigateToExperienceDetail(experienceId: Long) {
    navigate("$ExperiencesBaseRouteName/$experienceId")
}

fun NavGraphBuilder.experienceDetailScreen() {
    composable(
        route = ExperienceDetailRouteName,
        arguments = listOf(
            navArgument(ExperienceIdArgumentName) {
                type = NavType.LongType
            }
        )
    ) {
        ExperienceDetailScreen(experienceId = it.arguments?.getLong(ExperienceIdArgumentName))
    }
}