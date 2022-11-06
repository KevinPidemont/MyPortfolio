package com.myportfolio.experience.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.myportfolio.experience.view.ExperienceDetailScreen
import com.myportfolio.experience.view.MyExperiencesScreen


private const val ExperiencesBaseRouteName = "experiences"
private const val ExperienceIdArgumentName = "experience_id"
private const val ExperienceDetailRouteName = "$ExperiencesBaseRouteName/{$ExperienceIdArgumentName}"

fun NavController.navigateToMyExperiences(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ExperiencesBaseRouteName, builder = builder)
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