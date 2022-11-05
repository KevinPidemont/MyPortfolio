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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myportfolio.R
import com.myportfolio.base.model.Routes
import com.myportfolio.experiences.view.ExperienceDetailScreen
import com.myportfolio.experiences.view.MyExperiencesScreen
import com.myportfolio.profile.view.MyProfileScreen
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
                        id = Routes.MyProfile.name,
                        title = R.string.my_profile,
                        icon = Icons.Outlined.Person
                    ),
                    BottomNavBarItemData(
                        id = Routes.MyExperiences.name,
                        title = R.string.my_experiences,
                        icon = Icons.Outlined.ListAlt
                    ),
                    BottomNavBarItemData(
                        id = Routes.MyProjects.name,
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
                NavHost(navController = navController, startDestination = Routes.MyProfile.name) {
                    composable(Routes.MyProfile.name) {
                        MyProfileScreen()
                    }

                    composable(Routes.MyExperiences.name) {
                        MyExperiencesScreen(navigateTo)
                    }

                    composable(Routes.MyProjects.name) {
                        MyProjectScreen()
                    }

                    composable(
                        Routes.RoutesWithArguments.ExperienceDetail.name,
                        arguments = listOf(
                            navArgument("experience_id") {
                                type = NavType.LongType
                            }
                        )
                    ) { backStackEntry ->
                        ExperienceDetailScreen(experienceId = backStackEntry.arguments?.getLong("experience_id"))
                    }
                }
            }
        }
    )
}