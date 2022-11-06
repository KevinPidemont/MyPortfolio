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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.myportfolio.R
import com.myportfolio.experience.navigation.experienceDetailScreen
import com.myportfolio.experience.navigation.myExperiencesScreen
import com.myportfolio.experience.navigation.navigateToExperienceDetail
import com.myportfolio.experience.navigation.navigateToMyExperiences
import com.myportfolio.profile.navigation.ProfileRouteName
import com.myportfolio.profile.navigation.myProfileScreen
import com.myportfolio.profile.navigation.navigateToMyProfile
import com.myportfolio.project.navigation.myProjectsScreen
import com.myportfolio.project.navigation.navigateToMyProjects
import com.myportfolio.project.navigation.navigateToProjectDetail
import com.myportfolio.project.navigation.projectDetailScreen

@Composable
fun AppContent() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()

    // Automatically select the profile tab when there is no other destination left in the queue
    navController.addOnDestinationChangedListener { controller, _, _ ->
        // Size is equals to 2, because there is the root navigation destination + the profile destination
        if (controller.backQueue.size == 2 && selectedTabIndex != 0) {
            selectedTabIndex = 0
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavBarItemData(
                        title = R.string.my_profile,
                        icon = Icons.Outlined.Person
                    ),
                    BottomNavBarItemData(
                        title = R.string.my_experiences,
                        icon = Icons.Outlined.ListAlt
                    ),
                    BottomNavBarItemData(
                        title = R.string.my_projects,
                        icon = Icons.Outlined.Code
                    )
                ),
                selectedIndex = selectedTabIndex,
                onSelected = { index ->
                    selectedTabIndex = index

                    val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
                        // Pop up to the start destination of the selected bottom nav item
                        popUpTo(navController.graph.findStartDestination().id) {
                            // Save the state of all the destination that have been popped up
                            saveState = true
                        }
                        // Avoid launching new screen when the bottom nav item is reselected
                        launchSingleTop = true
                        // Restore the state when the bottom nav item is selected
                        restoreState = true
                    }

                    when (index) {
                        0 -> navController.navigateToMyProfile(navOptionsBuilder)
                        1 -> navController.navigateToMyExperiences(navOptionsBuilder)
                        2 -> navController.navigateToMyProjects(navOptionsBuilder)
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                NavHost(
                    navController = navController,
                    startDestination = ProfileRouteName
                ) {
                    myProfileScreen()

                    myExperiencesScreen { experienceId ->
                        navController.navigateToExperienceDetail(experienceId)
                    }
                    experienceDetailScreen()

                    myProjectsScreen { projectId ->
                        navController.navigateToProjectDetail(projectId)
                    }
                    projectDetailScreen()
                }
            }
        }
    )
}