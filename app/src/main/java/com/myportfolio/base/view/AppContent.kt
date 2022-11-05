package com.myportfolio.base.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myportfolio.R
import com.myportfolio.base.model.RouteArgument
import com.myportfolio.base.model.Routes
import com.myportfolio.base.model.Routes.Companion.getSingleArgName
import com.myportfolio.experiences.view.ExperienceDetailScreen
import com.myportfolio.experiences.view.MyExperiencesScreen
import com.myportfolio.profile.view.MyProfileScreen
import com.myportfolio.projects.ui.view.ProjectDetailScreen
import com.myportfolio.projects.view.MyProjectScreen

@Composable
fun AppContent() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()

    val navigateTo: (String) -> Unit = {
        navController.navigate(it)
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavBarItemData(
                        id = Routes.MyProfile.completeName,
                        title = R.string.my_profile,
                        icon = Icons.Outlined.Person
                    ),
                    BottomNavBarItemData(
                        id = Routes.MyExperiences.completeName,
                        title = R.string.my_experiences,
                        icon = Icons.Outlined.ListAlt
                    ),
                    BottomNavBarItemData(
                        id = Routes.MyProjects.completeName,
                        title = R.string.my_projects,
                        icon = Icons.Outlined.Code
                    )
                ),
                selectedIndex = selectedTabIndex,
                onSelected = { tabId, index ->
                    navController.navigate(tabId)
                    selectedTabIndex = index
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MyProfile.completeName
                ) {
                    composable(Routes.MyProfile.completeName) {
                        MyProfileScreen()
                    }

                    composable(Routes.MyExperiences.completeName) {
                        MyExperiencesScreen(navigateTo)
                    }

                    composable(Routes.MyProjects.completeName) {
                        MyProjectScreen(navigateTo = navigateTo)
                    }

                    composable(
                        Routes.ExperienceDetail.completeName,
                        arguments = Routes.ExperienceDetail.arguments.toNavArguments(NavType.LongType)
                    ) { backStackEntry ->
                        ExperienceDetailScreen(experienceId = backStackEntry.arguments?.getLong(Routes.ExperienceDetail.getSingleArgName()))
                    }

                    composable(
                        Routes.ProjectDetail.completeName,
                        arguments = Routes.ProjectDetail.arguments.toNavArguments(NavType.LongType)
                    ) { backStackEntry ->
                        ProjectDetailScreen(projectId = backStackEntry.arguments?.getLong(Routes.ProjectDetail.getSingleArgName()))
                    }
                }
            }
        }
    )
}

private fun List<RouteArgument>.toNavArguments(vararg navType: NavType<*>): List<NamedNavArgument> {
    if (navType.size != this.size) {
        throw IllegalStateException("The provided list of nav types must have the same length as the list of declared arguments!")
    }

    return mapIndexed { index, routeArgument -> routeArgument.toNavArgument(navType[index]) }
}

private fun RouteArgument.toNavArgument(navType: NavType<*>): NamedNavArgument {
    return navArgument(name) {
        type = navType
    }
}