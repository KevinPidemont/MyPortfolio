package com.myportfolio.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.myportfolio.profile.view.MyProfileScreen

const val ProfileRouteName = "profile"

fun NavController.navigateToMyProfile(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ProfileRouteName, builder = builder)
}

fun NavGraphBuilder.myProfileScreen() {
    composable(ProfileRouteName) {
        MyProfileScreen()
    }
}