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

                    when (index) {
                        0 -> navController.navigateToMyProfile()
                        1 -> navController.navigateToMyExperiences()
                        2 -> navController.navigateToMyProjects()
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